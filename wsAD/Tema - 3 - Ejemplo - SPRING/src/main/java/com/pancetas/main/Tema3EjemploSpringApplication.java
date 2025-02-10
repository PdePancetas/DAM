package com.pancetas.main;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pancetas.main.modelo.Empleado;
import com.pancetas.main.repository.*;

@SpringBootApplication
public class Tema3EjemploSpringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Tema3EjemploSpringApplication.class, args);
		
		
		EmpleadoRepository empRepo = context.getBean(EmpleadoRepository.class);
		DepartamentoRepository depRepo = context.getBean(DepartamentoRepository.class);
		
		List<Empleado> emps = empRepo.findAll();
		emps.stream().forEach(System.out::println);
		
		
		empRepo.findByNombre("a").forEach(System.out::println);
		
	}

}
