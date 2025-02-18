package com.dam2.proyectos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dam2.proyectos.repository.EmpleadoRepository;
import com.dam2.proyectos.repository.ProyectoRepository;
import com.dam2.proyectos.service.EmpleadoService;
import com.dam2.proyectos.service.ProyectoService;
import com.dam2.proyectos.model.*;
@SpringBootApplication
public class AppProyectosApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AppProyectosApplication.class, args);
		
		EmpleadoService empService = context.getBean(EmpleadoService.class);
		ProyectoService proyService = context.getBean(ProyectoService.class);
		
		//empService.modificaNombre("222222", "ANA");
		//empService.ponerComoJefe("222222", 2);
//		empService.asignarProyecto("111111", 2);
		
//		EmpleadoRepository empRepo = context.getBean(EmpleadoRepository.class);
//		ProyectoRepository proyRepo = context.getBean(ProyectoRepository.class);
//		empRepo.findAll().stream().forEach(System.out::println);
//		
//		Proyecto p = proyRepo.findById(1).get();
//		Empleado emp = empRepo.findById("222222").get();
//		
//		//cambiar alguna propiedad del empleado:
//		emp.setNom_emp("Juanito");
//		empRepo.save(emp);
//		
//		//cambiar el jefe del proyecto:
//		p.setEmpJefe(emp);
//		proyRepo.save(p);
//		proyRepo.flush(); 
//		
		
		
	}

}
