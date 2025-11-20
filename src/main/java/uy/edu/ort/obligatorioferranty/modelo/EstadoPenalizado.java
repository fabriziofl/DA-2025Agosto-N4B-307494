package uy.edu.ort.obligatorioferranty.modelo;

public class EstadoPenalizado extends Estado {

    public EstadoPenalizado() {
        super("Penalizado");
    }

    @Override
    public boolean puedeTransitar() {
        return true;
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
