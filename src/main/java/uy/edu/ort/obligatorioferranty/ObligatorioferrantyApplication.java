package uy.edu.ort.obligatorioferranty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uy.edu.ort.obligatorioferranty.modelo.DatosDePrueba;

@SpringBootApplication
public class ObligatorioferrantyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioferrantyApplication.class, args);
		DatosDePrueba.cargar();
	}

}
