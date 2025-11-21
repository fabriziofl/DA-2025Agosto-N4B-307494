package uy.edu.ort.obligatorioferranty.modelo;

import java.time.LocalDateTime;

public class Asignacion {

    private UsuarioPropietario propietario;
    private Bonificacion bonificacion;
    private Puesto puesto;
    private LocalDateTime fechaAsignacion;
    private Transito transito;

    public Asignacion(UsuarioPropietario propietario, Bonificacion bonificacion, Puesto puesto,
            LocalDateTime fechaAsignacion,
            Transito transito) {
        this.propietario = propietario;
        this.bonificacion = bonificacion;
        this.puesto = puesto;
        this.fechaAsignacion = fechaAsignacion;
        this.transito = transito;
    }

    public Transito getTransito() {
        return transito;
    }

    public void setTransito(Transito transito) {
        this.transito = transito;
    }

    public UsuarioPropietario getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioPropietario propietario) {
        this.propietario = propietario;
    }

    public Bonificacion getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Bonificacion bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

}
