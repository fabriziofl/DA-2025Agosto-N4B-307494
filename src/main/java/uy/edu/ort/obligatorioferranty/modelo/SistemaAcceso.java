package uy.edu.ort.obligatorioferranty.modelo;

import java.util.ArrayList;

public class SistemaAcceso {

    private ArrayList<UsuarioAdministrador> administradores = new ArrayList<>();
    private ArrayList<UsuarioPropietario> propietarios = new ArrayList<>();
    private ArrayList<Sesion> sesionesActivas = new ArrayList<>();

    public void agregarUsuarioPropietario(String documento, String contrasenia, String nombreCompleto, Estado estado) {
        UsuarioPropietario nuevoPropietario = new UsuarioPropietario(documento, contrasenia, nombreCompleto, 4000, 500,
                estado, null, null, null, null);
        propietarios.add(nuevoPropietario);
    }

    public void agregarUsuarioPropietario(UsuarioPropietario usuarioPropietario) {
        propietarios.add(usuarioPropietario);
    }

    public void agregarUsuarioAdministrador(String documento, String contrasenia, String nombreCompleto) {
        UsuarioAdministrador nuevoAdministrador = new UsuarioAdministrador(documento, contrasenia, nombreCompleto);
        administradores.add(nuevoAdministrador);
    }

    public Sesion loginPropietario(String documento, String contrasenia, Estado estado) throws PeajeException {
        Sesion sesion = null;
        UsuarioPropietario usuario = (UsuarioPropietario) login(documento, contrasenia, propietarios);

        if (usuario == null) {
            throw new PeajeException("Acceso denegado");
        }
        // Verificar estado del usuario
        // Si el estado es Deshabilitado, no puede ingresar al sistema

        if (usuario.getEstado() instanceof EstadoDeshabilitado) {
            // Curso alternativo: usuario deshabilitado
            throw new PeajeException("Usuario deshabilitado, no puede ingresar al sistema");
        }

        sesion = new Sesion(usuario);
        sesionesActivas.add(sesion);
        return sesion;
    }

    public Sesion loginAdministrador(String documento, String contrasenia) throws PeajeException {
        Sesion sesion = null;
        UsuarioAdministrador administrador = (UsuarioAdministrador) login(documento, contrasenia, administradores);

        if (administrador == null) {
            throw new PeajeException("Acceso denegado");
        }
        // Verificar estado del usuario
        // Si el estado es Deshabilitado, no puede ingresar al sistema

        sesion = new Sesion(administrador);
        sesionesActivas.add(sesion);
        return sesion;
    }

    private Usuario login(String documento, String contrasenia, ArrayList listaUsuarios) {
        for (Object objUsuario : listaUsuarios) {
            Usuario usuario = (Usuario) objUsuario;
            if (usuario.getDocumento().equals(documento) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    public ArrayList<Sesion> getSesionesActivas() {
        return sesionesActivas;
    }

    public void logout(Sesion sesion) {
        sesionesActivas.remove(sesion);
    }
}
