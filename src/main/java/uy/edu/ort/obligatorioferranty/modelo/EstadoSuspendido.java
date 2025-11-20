package uy.edu.ort.obligatorioferranty.modelo;

public class EstadoSuspendido extends Estado {

    public EstadoSuspendido() {
        super("Suspendido");
    }

    @Override
    public boolean puedeTransitar() {
        return false;
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
