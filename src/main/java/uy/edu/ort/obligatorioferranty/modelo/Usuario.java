package uy.edu.ort.obligatorioferranty.modelo;

public abstract class Usuario {

    private String documento;
    private String contrasenia;

    private String nombreCompleto;

    public Usuario(String documento, String contrasenia, String nombreCompleto) {
        this.documento = documento;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
