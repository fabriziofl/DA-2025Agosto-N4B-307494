package uy.edu.ort.obligatorioferranty.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import uy.edu.ort.obligatorioferranty.modelo.Asignacion;
import uy.edu.ort.obligatorioferranty.modelo.Bonificacion;
import uy.edu.ort.obligatorioferranty.modelo.Fachada;
import uy.edu.ort.obligatorioferranty.modelo.Transito;
import uy.edu.ort.obligatorioferranty.modelo.UsuarioPropietario;
import uy.edu.ort.obligatorioferranty.modelo.Vehiculo;

/**
 * DTO que agrupa los datos necesarios para mostrar el tablero del propietario.
 */
public class PropietarioDto {

    private String nombre;
    private String apellido;
    private String estado;
    private double saldo;

    private List<BonificacionDto> bonificaciones = new ArrayList<>();
    private List<VehiculoDto> vehiculos = new ArrayList<>();
    private List<TransitoDto> transitos = new ArrayList<>();
    private List<NotificacionDto> notificaciones = new ArrayList<>();

    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy");

    public PropietarioDto(UsuarioPropietario u) {
        if (u == null)
            return;

        String nombreCompleto = u.getNombreCompleto() != null ? u.getNombreCompleto().trim() : "";
        if (!nombreCompleto.isEmpty()) {
            String[] parts = nombreCompleto.split("\\s+");
            this.nombre = parts[0];
            this.apellido = parts.length > 1 ? parts[parts.length - 1] : "";
        } else {
            this.nombre = "";
            this.apellido = "";
        }

        this.estado = u.getEstado() != null ? u.getEstado().getNombre() : "";
        this.saldo = u.getSaldoActual();

        // bonificaciones desde asignaciones
        if (u.getAsignaciones() != null) {
            for (Asignacion a : u.getAsignaciones()) {
                BonificacionDto b = new BonificacionDto();
                b.bonificacion = a.getBonificacion() != null ? a.getBonificacion().getNombre() : "";
                b.puesto = a.getPuesto() != null ? a.getPuesto().getNombre() : "";
                LocalDateTime d = a.getFechaAsignacion();
                b.fecha = d != null ? d.toLocalDate().toString() : "";
                bonificaciones.add(b);
            }
        }

        // vehiculos
        if (u.getVehiculos() != null) {
            for (Vehiculo v : u.getVehiculos()) {
                VehiculoDto vd = new VehiculoDto();
                vd.matricula = v.getMatricula();
                vd.modelo = v.getModelo();
                vd.color = v.getColor();
                // por ahora no hay contador de tránsitos por vehículo en el modelo, dejamos 0
                vd.transitos = 0;
                vd.montoTotal = 0.0;
                vehiculos.add(vd);
            }
        }

        // transitos del propietario (si están disponibles en la entidad)
        if (u.getTransitosActivos() != null) {
            for (Transito t : u.getTransitosActivos()) {
                TransitoDto td = new TransitoDto();
                td.puesto = t.getPuesto() != null ? t.getPuesto().getNombre() : "";
                td.matricula = t.getMatricula();
                td.categoria = (t.getTarifa() != null && t.getTarifa().getCategoria() != null)
                        ? t.getTarifa().getCategoria().getNombre()
                        : "";
                td.montoTarifa = t.getMonto();

                // 🔹 usar el modelo para saber qué bonificación aplica
                Bonificacion bonif = Fachada.getInstancia()
                        .obtenerBonificacionParaTransito(u, t);

                if (bonif != null) {
                    td.bonificacion = bonif.getNombre();
                    td.montoBonificado = Fachada.getInstancia()
                            .obtenerMontoBonificado(u, t);
                } else {
                    td.bonificacion = "";
                    td.montoBonificado = 0;
                }

                td.montoPagado = td.montoTarifa - td.montoBonificado;

                LocalDateTime fh = t.getFechaHora();
                if (fh != null) {
                    LocalDate date = fh.toLocalDate();
                    td.fecha = date.toString();
                    td.hora = fh.toLocalTime().toString();
                } else {
                    td.fecha = "";
                    td.hora = "";
                }

                transitos.add(td);
            }
        }

        // notificaciones: como la clase Notificacion no expone campos, devolvemos un
        // placeholder por cada elemento
        if (u.getNotificaciones() != null) {
            int idx = 1;
            for (Object n : u.getNotificaciones()) {
                NotificacionDto nd = new NotificacionDto();
                nd.mensaje = "Notificación #" + idx++; // placeholder
                notificaciones.add(nd);
            }
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEstado() {
        return estado;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<BonificacionDto> getBonificaciones() {
        return bonificaciones;
    }

    public List<VehiculoDto> getVehiculos() {
        return vehiculos;
    }

    public List<TransitoDto> getTransitos() {
        return transitos;
    }

    public List<NotificacionDto> getNotificaciones() {
        return notificaciones;
    }

    // DTO inner classes
    public static class BonificacionDto {
        private String bonificacion;
        private String puesto;
        private String fecha;

        public String getBonificacion() {
            return bonificacion;
        }

        public String getPuesto() {
            return puesto;
        }

        public String getFecha() {
            return fecha;
        }
    }

    public static class VehiculoDto {
        private String matricula;
        private String modelo;
        private String color;
        private int transitos;
        private double montoTotal;

        public String getMatricula() {
            return matricula;
        }

        public String getModelo() {
            return modelo;
        }

        public String getColor() {
            return color;
        }

        public int getTransitos() {
            return transitos;
        }

        public double getMontoTotal() {
            return montoTotal;
        }
    }

    public static class TransitoDto {
        private String puesto;
        private String matricula;
        private String categoria;
        private double montoTarifa;
        private String bonificacion; // nombre de la bonificación
        private double montoBonificado; // cuánto se descontó
        private double montoPagado; // tarifa - bonificación
        private String fecha;
        private String hora;

        public String getPuesto() {
            return puesto;
        }

        public String getMatricula() {
            return matricula;
        }

        public String getCategoria() {
            return categoria;
        }

        public double getMontoTarifa() {
            return montoTarifa;
        }

        public String getBonificacion() {
            return bonificacion;
        }

        public double getMontoBonificado() {
            return montoBonificado;
        }

        public double getMontoPagado() {
            return montoPagado;
        }

        public String getFecha() {
            return fecha;
        }

        public String getHora() {
            return hora;
        }
    }

    public static class NotificacionDto {
        private String mensaje;

        public String getMensaje() {
            return mensaje;
        }
    }

}