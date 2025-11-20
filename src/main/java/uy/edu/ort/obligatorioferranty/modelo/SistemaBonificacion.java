package uy.edu.ort.obligatorioferranty.modelo;

import java.util.ArrayList;
import java.util.List;

public class SistemaBonificacion {

    public List<Bonificacion> bonificaciones = new ArrayList<>();

    public void agregarBonificacion(Bonificacion bonificacion) {
        bonificaciones.add(bonificacion);
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }
}
