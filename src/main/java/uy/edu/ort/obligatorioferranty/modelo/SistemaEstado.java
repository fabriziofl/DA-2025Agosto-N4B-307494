package uy.edu.ort.obligatorioferranty.modelo;

import java.util.ArrayList;
import java.util.List;

public class SistemaEstado {

    private List<Estado> estados = new ArrayList<>();
    private String estadoDefault = "Habilitado";

    public SistemaEstado() {
        precargarEstados();
    }

    private void precargarEstados() {
        estados.add(new EstadoHabilitado());
        estados.add(new EstadoDeshabilitado());
        estados.add(new EstadoSuspendido());
        estados.add(new EstadoPenalizado());
    }

    public Estado obtener(String nombreEstado) {
        for (Estado estado : estados) {
            if (estado.getNombre().equalsIgnoreCase(nombreEstado)) {
                return estado;
            }
        }
        return null;
    }

    public Estado getDefault() {
        return obtener(estadoDefault);
    }

    public List<Estado> getEstados() {
        return estados;
    }
}
