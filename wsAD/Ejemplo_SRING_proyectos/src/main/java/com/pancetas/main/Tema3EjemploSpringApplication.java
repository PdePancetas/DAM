package com.pancetas.main;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pancetas.main.modelo.Empleado;
import com.pancetas.main.modelo.Proyecto;
import com.pancetas.main.repository.*;
import com.pancetas.main.service.EmpleadoService;
import com.pancetas.main.service.ProyectoService;

@SpringBootApplication
public class Tema3EjemploSpringApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Tema3EjemploSpringApplication.class, args);
		
		//Listar los empleados asociados a un proyecto
		///////////////////////////////
//		proyRepo.findById(16).ifPresent(p -> p.getEmpleados().forEach(System.out::println));
		///////////////////////////////
		//Listar todos los empleados
//		empRepo.findAll().forEach(System.out::println);
				
		EmpleadoService empService = context.getBean(EmpleadoService.class);
//		empService.cambiarNombre("123", "Rubenaldo");
//		empService.asignarProyecto("123", 16);
		
		ProyectoService proyService = context.getBean(ProyectoService.class);
		
//		proyService.cambiarJefeProyecto(16, "1");
		
//		empService.findEmps("Rubenaldo").forEach(System.out::println);
//		proyService.ProySuperanId(15).forEach(System.out::println);
//		empService.getJefesFromNomProy("p1").forEach(System.out::println);
		
	}

}
