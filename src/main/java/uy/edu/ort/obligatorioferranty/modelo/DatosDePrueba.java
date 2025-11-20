package uy.edu.ort.obligatorioferranty.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatosDePrueba {

    public static void cargar() {
        Fachada fachada = Fachada.getInstancia();

        UsuarioPropietario propietario1 = new UsuarioPropietario(
                "47544433",
                "123",
                "Fabrizio Ferranty",
                500,
                4,
                new EstadoHabilitado(), null, null, null, null);

        Categoria categoria1 = new Categoria("Automovil");
        List<Vehiculo> vehiculos1 = new ArrayList<>();
        vehiculos1.add(new Vehiculo("ABC1234", "Toyota", "Corolla", categoria1, propietario1));
        vehiculos1.add(new Vehiculo("DEF5678", "Honda", "Civic", categoria1, propietario1));
        propietario1.setVehiculos(vehiculos1);

        List<Notificacion> notificaciones1 = new ArrayList<>();
        notificaciones1.add(new Notificacion("Notificacion alerta"));
        propietario1.setNotificaciones(notificaciones1);

        List<Tarifa> tarifas1 = new ArrayList<>();

        Tarifa tarifa1 = new Tarifa(categoria1, 4000);
        Tarifa tarifa2 = new Tarifa(new Categoria("Moto"), 2000);
        tarifas1.add(tarifa1);
        tarifas1.add(tarifa2);

        List<Bonificacion> bonificaciones1 = new ArrayList<>();
        Bonificacion bonificacion1 = new BonificacionExonerados();
        bonificaciones1.add(bonificacion1);
        Bonificacion bonificacion2 = new BonificacionFrecuentes();
        bonificaciones1.add(bonificacion2);

        LocalDateTime fechaHora = LocalDateTime.now();
        LocalDateTime fechaHoraPosterior = fechaHora.plusHours(1);

        List<Transito> transitos1 = new ArrayList<>();
        List<Transito> transitos2 = new ArrayList<>();
        List<Transito> transitosPuesto2 = new ArrayList<>();

        Puesto puesto1 = new Puesto("San jose", "Islas canarias", transitos1, tarifas1);
        Puesto puesto2 = new Puesto("Canelones", "Av. Italia", transitos2, tarifas1);

        Transito transito1 = new Transito(puesto1, "ABC1234", fechaHora, tarifa1);
        transitos1.add(transito1);

        Transito transito2 = new Transito(puesto2, "DEF5678", fechaHora, tarifa2);
        transitos1.add(transito2);
        propietario1.setTransitos(transitos1);

        Transito transito3 = new Transito(puesto2, "DEF5678", fechaHoraPosterior, tarifa2);
        transitos1.add((transito3));
        transitosPuesto2.add(transito3);
        transitosPuesto2.add(transito2);
        puesto1.setTransitos(transitos1);
        puesto2.setTransitos(transitosPuesto2);

        List<Asignacion> asignaciones1 = new ArrayList<>();
        Asignacion asignacion1 = new Asignacion((propietario1), bonificacion1, puesto1, fechaHora, transito1);
        asignaciones1.add(asignacion1);
        Asignacion asignacion2 = new Asignacion((propietario1), bonificacion2, puesto2, fechaHora, transito2);
        asignaciones1.add(asignacion2);
        propietario1.setAsignaciones(asignaciones1);

        fachada.agregarUsuarioPropietario("12345678", "pass123", "Juan Perez", null);
        fachada.agregarUsuarioPropietario("1", "1", "Maria Gomez", new EstadoDeshabilitado());
        fachada.agregarUsuarioPropietario(propietario1);

        fachada.agregarUsuarioAdministrador("456", "456", "Fabian Fernandez");
        fachada.agregarUsuarioAdministrador("777", "777", "Guillermo Machado");

    }
}