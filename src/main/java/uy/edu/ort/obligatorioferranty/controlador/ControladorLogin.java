package uy.edu.ort.obligatorioferranty.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import uy.edu.ort.obligatorioferranty.modelo.Fachada;
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
            @RequestParam String contrasenia) throws Exception {
        Sesion sesion = (Sesion) sessionHttp.getAttribute("usuarioAdministrador");
        if (sesion != null) {
            throw new Exception("Ud. Ya está logueado”.");
        }

        // login al modelo
        sesion = (Sesion) Fachada.getInstancia().loginAdministrador(documento, contrasenia);

        // si hay una sesion abierta la cierro
        logoutAdministrador(sessionHttp);

        // guardo la ssion de la logica en la sesion http
        sessionHttp.setAttribute("usuarioAdministrador", sesion);
        return Respuesta.lista(new Respuesta("loginExitoso", "vista-exito.html"));
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
