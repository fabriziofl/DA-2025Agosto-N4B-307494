package uy.edu.ort.obligatorioferranty.modelo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import observador.Observable;

public class Fachada extends Observable {

    private SistemaAcceso sistemaAcceso = new SistemaAcceso();
    private SistemaAsignacion sistemaAsignacion = new SistemaAsignacion();
    private SistemaPuesto sistemaPuestos = new SistemaPuesto();

    // singleton
    private static Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }

    private Fachada() {
    }

    // DELEGACIONES

    public List<Puesto> getPuestos() {
        return sistemaPuestos.getPuestos();
    }

    public Sesion loginPropietario(String documento, String contrasenia, Estado estado) throws Exception {
        return sistemaAcceso.loginPropietario(documento, contrasenia, estado);
    }

    public Sesion loginAdministrador(String documento, String contrasenia) throws PeajeException {
        return sistemaAcceso.loginAdministrador(documento, contrasenia);
    }

    public void logout(Sesion sesion) {
        sistemaAcceso.logout(sesion);
    }

    public void agregarUsuarioPropietario(String documento, String contrasenia, String nombreCompleto, Estado estado) {
        sistemaAcceso.agregarUsuarioPropietario(documento, contrasenia, nombreCompleto, estado);
    }

    public void agregarUsuarioPropietario(UsuarioPropietario usuarioPropietario) {
        sistemaAcceso.agregarUsuarioPropietario(usuarioPropietario);
    }

    public void agregarUsuarioAdministrador(String documento, String contrasenia, String nombreCompleto) {
        sistemaAcceso.agregarUsuarioAdministrador(documento, contrasenia, nombreCompleto);
    }

    public ArrayList<Sesion> getSesionesActivas() {
        return sistemaAcceso.getSesionesActivas();
    }

    public void agregarAsignacion(UsuarioPropietario propietario, Bonificacion bonificacion, Puesto puesto,
            LocalDateTime fechaAsignacion, Transito transito) {
        sistemaAsignacion.agregarAsignacion(propietario, bonificacion, puesto, fechaAsignacion, transito);
    }

    public Bonificacion obtenerBonificacionParaTransito(UsuarioPropietario propietario, Transito transito) {
        return sistemaAsignacion.obtenerBonificacionParaTransito(propietario, transito);
    }

    public double obtenerMontoBonificado(UsuarioPropietario propietario, Transito transito) {
        return sistemaAsignacion.obtenerMontoBonificado(propietario, transito);
    }

}
