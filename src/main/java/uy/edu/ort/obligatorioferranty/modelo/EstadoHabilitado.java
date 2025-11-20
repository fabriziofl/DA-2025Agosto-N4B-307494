package uy.edu.ort.obligatorioferranty.modelo;

public class EstadoHabilitado extends Estado {

    public EstadoHabilitado() {
        super("Habilitado");
    }

    @Override
    public boolean puedeTransitar() {
        return true;
    }

    @Override
    public boolean puedeBonificar() {
        return true;
    }

    @Override
    public boolean puedeNotificar() {
        return true;
    }

}
