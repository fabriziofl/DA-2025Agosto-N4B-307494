package uy.edu.ort.obligatorioferranty.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ort.obligatorioferranty.modelo.Sesion;

public class SesionDto {

    private String fechaIngreso;
    private String documento;
    private String nombreCompleto;

    public SesionDto(Sesion sesion) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        fechaIngreso = sdf.format(sesion.getFechaIngreso());
        documento = sesion.getUsuario().getDocumento();
        nombreCompleto = sesion.getUsuario().getNombreCompleto();

    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public static List<SesionDto> listaSesionesDto(List<Sesion> sesiones) {

        List<SesionDto> sesionDtos = new ArrayList<>();
        for (Sesion sesion : sesiones) {
            sesionDtos.add(new SesionDto(sesion));
        }
        return sesionDtos;
    }

}
