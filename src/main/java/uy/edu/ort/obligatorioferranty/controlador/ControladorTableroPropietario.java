package uy.edu.ort.obligatorioferranty.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.server.ResponseStatusException;

import uy.edu.ort.obligatorioferranty.ConexionNavegador;
import uy.edu.ort.obligatorioferranty.dto.PropietarioDto;
import uy.edu.ort.obligatorioferranty.dto.SesionDto;
import uy.edu.ort.obligatorioferranty.modelo.Fachada;
import uy.edu.ort.obligatorioferranty.modelo.Sesion;
import observador.Observable;
import observador.Observador;

@RestController
@RequestMapping("/propietario")
@Scope("session")
public class ControladorTableroPropietario implements Observador {

    private final ConexionNavegador conexionNavegador;

    public ControladorTableroPropietario(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {
        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();
    }

    @PostMapping("/vistaConectada")
    public List<Respuesta> inicializarVista(@SessionAttribute(name = "usuarioPropietario") Sesion sesion) {
        Fachada.getInstancia().agregarObservador(this);
        return Respuesta.lista(
                sesiones(),
                new Respuesta("nombrePropietario", sesion.getUsuario().getNombreCompleto()));
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        // Implementación inspirada en tu ejemplo: reaccionar solo a eventos concretos
        try {
            if (conexionNavegador.getConexionSSE() == null)
                return;

            if (evento instanceof Respuesta) {
                conexionNavegador.enviarJSON(Respuesta.lista((Respuesta) evento));
                return;
            }

            if (evento instanceof List) {
                conexionNavegador.enviarJSON(evento);
                return;
            }

            if (evento instanceof String) {
                String ev = (String) evento;
                // Ajustá los nombres de eventos a los que tu modelo realmente emite
                if (ev.equals("cambioListaContactos") || ev.equals("cambioEstado")
                        || ev.equals("cambioSesiones") || ev.equals("sesionesActualizadas")) {
                    conexionNavegador.enviarJSON(Respuesta.lista(sesiones()));
                    return;
                }
            }

            // Fallback: enviar el evento envuelto en una Respuesta para que la vista lo
            // reciba
            conexionNavegador.enviarJSON(Respuesta.lista(new Respuesta("evento", evento)));

        } catch (Throwable t) {
            System.out.println("Error notificando al navegador: " + t.getMessage());
            try {
                conexionNavegador.cerrarConexion();
            } catch (Throwable ignore) {
            }
        }
    }

    private Respuesta sesiones() {
        return new Respuesta("sesiones", SesionDto.listaSesionesDto(Fachada.getInstancia().getSesionesActivas()));
    }

    @GetMapping("/datos")
    public PropietarioDto datosPropietario(
            @SessionAttribute(name = "usuarioPropietario", required = false) Sesion sesion) {
        if (sesion == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no logueado");
        }
        return new PropietarioDto(sesion.getUsuario());
    }
}
