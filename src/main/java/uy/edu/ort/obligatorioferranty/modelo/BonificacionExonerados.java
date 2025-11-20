package uy.edu.ort.obligatorioferranty.modelo;

public class BonificacionExonerados extends Bonificacion {

    public BonificacionExonerados() {
        super("Exonerados");
    }

    public double devolverPrecioBonificado(Transito transito) {
        return transito.getMonto();
    }
}
