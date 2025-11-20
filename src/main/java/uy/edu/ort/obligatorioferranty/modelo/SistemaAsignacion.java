package uy.edu.ort.obligatorioferranty.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaAsignacion {

    private List<Asignacion> asignaciones = new ArrayList<>();

    public void agregarAsignacion(UsuarioPropietario propietario, Bonificacion bonificacion, Puesto puesto,
            LocalDateTime fechaAsignacion, Transito transito) {
        asignaciones.add(new Asignacion(propietario, bonificacion, puesto, fechaAsignacion, transito));
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    /**
     * Devuelve la bonificación asignada al propietario para el puesto de ese
     * tránsito.
     */
    public Bonificacion obtenerBonificacionParaTransito(UsuarioPropietario propietario, Transito transito) {
        if (propietario.getAsignaciones() == null)
            return null;

        for (Asignacion a : propietario.getAsignaciones()) {
            if (a.getPuesto() != null && a.getPuesto().equals(transito.getPuesto())) {
                return a.getBonificacion();
            }
        }
        return null;
    }

    /** Devuelve el MONTO bonificado (lo que se descuenta) para ese tránsito. */
    public double obtenerMontoBonificado(UsuarioPropietario propietario, Transito transito) {
        Bonificacion b = obtenerBonificacionParaTransito(propietario, transito);
        if (b == null)
            return 0;
        return b.devolverPrecioBonificado(transito);
    }
}
