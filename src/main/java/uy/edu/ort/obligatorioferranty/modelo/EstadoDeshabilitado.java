package uy.edu.ort.obligatorioferranty.modelo;

public class EstadoDeshabilitado extends Estado {

    public EstadoDeshabilitado() {
        super("Deshabilitado");
    }

    @Override
    public boolean puedeTransitar() {
        return false;
    }

    @Override
    public boolean puedeBonificar() {
        return false;
    }

    @Override
    public boolean puedeNotificar() {
        return false;
    }

}
