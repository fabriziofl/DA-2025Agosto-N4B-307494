package uy.edu.ort.obligatorioferranty.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BonificacionFrecuentes extends Bonificacion {

    public BonificacionFrecuentes() {
        super("Frecuentes");
    }

    // metodo para que el descuento se aplique solo si no es el primer transito en
    // el día por un puesto determinado
    // con el mismo vehículo
    public double devolverPrecioBonificado(Transito transito) {
        Puesto puesto = transito.getPuesto();
        String matricula = transito.getMatricula();
        LocalDateTime fecha = transito.getFechaHora();

        if (puesto.yaPasoHoy(matricula, fecha)) {
            // Descuento del 50%
            return transito.getMonto() * 0.5;
        } else {
            // Sin descuento
            return 0;
        }
    }
}