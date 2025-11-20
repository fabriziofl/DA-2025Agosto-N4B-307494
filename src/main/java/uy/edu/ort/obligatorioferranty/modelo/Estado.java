package uy.edu.ort.obligatorioferranty.modelo;

public abstract class Estado {

    private String nombre;

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract boolean puedeTransitar();

    public abstract boolean puedeBonificar();

    public abstract boolean puedeNotificar();
}
