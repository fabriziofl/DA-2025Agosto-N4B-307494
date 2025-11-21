package uy.edu.ort.obligatorioferranty.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import uy.edu.ort.obligatorioferranty.modelo.Fachada;
import uy.edu.ort.obligatorioferranty.modelo.PeajeException;
import uy.edu.ort.obligatorioferranty.modelo.Sesion;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/login")
public class ControladorLogin {

    @PostMapping("/loginPropietario")
    public List<Respuesta> loginPropietario(HttpSession sessionHttp, @RequestParam String documento,
            @RequestParam String contrasenia) throws Exception {
        // login al modelo
        Sesion sesion = (Sesion) Fachada.getInstancia().loginPropietario(documento, contrasenia, null);

        // si hay una sesion abierta la cierro
        logoutPropietario(sessionHttp);

        // guardo la ssion de la logica en la sesion http
        sessionHttp.setAttribute("usuarioPropietario", sesion);
        return Respuesta.lista(new Respuesta("loginExitoso", "tablero-propietario.html"));
    }

    @PostMapping("/logoutPropietario")
    public List<Respuesta> logoutPropietario(HttpSession sessionHttp) throws Exception {
        Sesion sesion = (Sesion) sessionHttp.getAttribute("usuarioPropietario");
        if (sesion != null) {
            // hago el logout en la logica
            Fachada.getInstancia().logout(sesion);
            // elimino la sesion http
            sessionHttp.removeAttribute("usuarioPropietario");
        }
        return Respuesta.lista(new Respuesta("logoutExitoso", "login.html"));
    }

    @PostMapping("/loginAdministrador")
    public List<Respuesta> loginAdministrador(HttpSession sessionHttp, @RequestParam String documento,
            @RequestParam String contrasenia) throws PeajeException {
        Sesion sesion = (Sesion) sessionHttp.getAttribute("usuarioAdministrador");
        if (sesion != null) {
            // Curso alternativo: administrador ya logueado
            return Respuesta.lista(
                    new Respuesta("errorLogin", "Ud. ya está logueado"),
                    new Respuesta("loginExitoso", "menu-administrador.html"));
        }

        try {
            // 2) Intentar login en la lógica
            sesion = Fachada.getInstancia().loginAdministrador(documento, contrasenia);

            // 3) Guardar la sesión de la lógica en la sesión HTTP
            sessionHttp.setAttribute("usuarioAdministrador", sesion);

            // 4) Curso normal: ir al menú del administrador
            return Respuesta.lista(
                    new Respuesta("loginExitoso", "menu-administrador.html"));

        } catch (PeajeException e) {
            // Curso alternativo: credenciales inválidas ("Acceso denegado")
            return Respuesta.lista(
                    new Respuesta("errorLogin", e.getMessage()));
        }
    }

    @PostMapping("/logoutAdministrador")
    public List<Respuesta> logoutAdministrador(HttpSession sessionHttp) throws Exception {
        Sesion sesion = (Sesion) sessionHttp.getAttribute("usuarioAdministrador");
        if (sesion != null) {
            // hago el logout en la logica
            Fachada.getInstancia().logout(sesion);
            // elimino la sesion http
            sessionHttp.removeAttribute("usuarioAdministrador");
        }
        return Respuesta.lista(new Respuesta("logoutExitoso", "login-administrador.html"));
    }
}
