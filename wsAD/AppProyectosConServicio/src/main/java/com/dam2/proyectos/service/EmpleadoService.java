package com.dam2.proyectos.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dam2.proyectos.model.Empleado;
import com.dam2.proyectos.model.Proyecto;
import com.dam2.proyectos.repository.EmpleadoRepository;
import com.dam2.proyectos.repository.ProyectoRepository;

@Service
public class EmpleadoService {
	
	//si ponemos esta anotación no hace falta
	//que pasemos esta propiedad por constructor,
	//Spring la busca
	@Autowired
    private ProyectoRepository proyRepo;

    @Autowired
    private EmpleadoRepository empRepo;
    
    //PARA QUE MANTENGA LA SESIÓN HIBERNATE ACTIVA TODO EL MÉTODO, IMPORTANTE
    @Transactional
    public void modificaNombre(String dni, String nombre) {
    	
    	Empleado emp = empRepo.findById(dni).get();
    	emp.setNom_emp(nombre);
    	empRepo.save(emp);
    	
    }
    
    
    @Transactional
    public void ponerComoJefe(String dni, int idProy) {
    	
    	Empleado emp = empRepo.findById(dni).get();
    	Proyecto p = proyRepo.findById(idProy).get();
    	
    	//Hibernate.initialize(emp.getLlevaProyectos());
    	
    	for(Proyecto proy : emp.getLlevaProyectos())
    		System.out.println(proy.getNom_proy());
    	
    	p.setEmpJefe(emp);
    	
    	
    	proyRepo.save(p);
    	//fuerza los cambios en BD si no se
    	//hubieran realizado:
    	proyRepo.flush();
    	
    }
    
    @Transactional
    public void asignarProyecto(String dni, int idProy) {
    	
    	Empleado emp = empRepo.findById(dni).get();
    	Proyecto p = proyRepo.findById(idProy).get();
    	
    	p.getEmpleados().add(emp);
    	
    	//p es el que guardamos porque es el propietario de la relación!!
    	proyRepo.save(p);
    	proyRepo.flush();
    	
    }

}
