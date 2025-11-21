package uy.edu.ort.obligatorioferranty.dto;

public class PuestoDto {
    private String nombre;
    private String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public PuestoDto(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

}
