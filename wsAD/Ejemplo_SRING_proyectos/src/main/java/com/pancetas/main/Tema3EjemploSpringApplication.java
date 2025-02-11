package com.pancetas.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pancetas.main.modelo.Empleado;
import com.pancetas.main.modelo.Proyecto;
import com.pancetas.main.repository.*;

@SpringBootApplication
public class Tema3EjemploSpringApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Tema3EjemploSpringApplication.class, args);
		Logger.getLogger("org.hibernate.orm.connections.pooling").setLevel(Level.SEVERE);
		
		EmpleadoRepository empRepo = context.getBean(EmpleadoRepository.class);
		ProyectoRepository proyRepo = context.getBean(ProyectoRepository.class);
		
		//Listar los empleados asociados a un proyecto
		///////////////////////////////
		proyRepo.findById(11).ifPresent(p -> p.getEmpleados().stream().forEach(System.out::println));
		///////////////////////////////
		
		//Asociar un empleado con un proyecto
		///////////////////////////////
		Empleado empleado = empRepo.findById("2").orElseThrow();
		Proyecto proyecto = proyRepo.findById(11).orElseThrow();
		empleado.getProyectosTrabaja().add(proyecto);
		empRepo.save(empleado);
		///////////////////////////////
		
	}

}
