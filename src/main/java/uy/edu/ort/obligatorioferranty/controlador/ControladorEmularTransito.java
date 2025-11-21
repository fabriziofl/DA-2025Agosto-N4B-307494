package uy.edu.ort.obligatorioferranty.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

import uy.edu.ort.obligatorioferranty.dto.PropietarioDto;
import uy.edu.ort.obligatorioferranty.dto.PuestoDto;
import uy.edu.ort.obligatorioferranty.modelo.Fachada;
import uy.edu.ort.obligatorioferranty.modelo.Puesto;
import uy.edu.ort.obligatorioferranty.modelo.Sesion;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/administrador")
@Scope("session")
public class ControladorEmularTransito {

    @GetMapping("/emularTransito/listarPuestos")
    public List<Respuesta> listaPuestos(
            @SessionAttribute(name = "usuarioAdministrador", required = false) Sesion sesion) {
        if (sesion == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no logueado");
        }
        List<Puesto> listaPuestos = Fachada.getInstancia().getPuestos();
        Respuesta r = new Respuesta("puestos", listaPuestos);

        return Respuesta.lista(r);
    }

}
