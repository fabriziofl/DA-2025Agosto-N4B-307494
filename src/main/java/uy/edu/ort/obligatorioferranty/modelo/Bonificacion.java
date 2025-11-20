package uy.edu.ort.obligatorioferranty.modelo;

public abstract class Bonificacion {

    private String nombre;

    public Bonificacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double devolverPrecioBonificado(Transito transito);

}
