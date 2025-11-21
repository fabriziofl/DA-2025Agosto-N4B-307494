package uy.edu.ort.obligatorioferranty.modelo;

import java.sql.Date;
import java.util.List;

public class Sesion {

    private Date fechaIngreso = new Date(System.currentTimeMillis());
    private UsuarioPropietario usuario;
    private UsuarioAdministrador usuarioAdmin;

    public UsuarioAdministrador getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public Sesion(UsuarioPropietario usuario) {
        this.usuario = usuario;
    }

    public Sesion(UsuarioAdministrador usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public UsuarioPropietario getUsuario() {
        return usuario;
    }

    public UsuarioAdministrador getUsuarioAdministrador() {
        return usuarioAdmin;
    }

    public List<Asignacion> getAsignacionesActivas() {
        return usuario.getAsignaciones();
    }

    public List<Vehiculo> getVehiculos() {
        return usuario.getVehiculos();
    }

    public List<Transito> getTransitosActivos() {
        return usuario.getTransitosActivos();
    }

}
