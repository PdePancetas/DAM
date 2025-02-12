package com.pancetas.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pancetas.main.modelo.Empleado;
import com.pancetas.main.modelo.Proyecto;
import com.pancetas.main.repository.EmpleadoRepository;
import com.pancetas.main.repository.ProyectoRepository;

@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empRepo;
	@Autowired
	ProyectoRepository proyRepo;

	public void cambiarNombre(String dni, String nuevoNombre) {
		Empleado emp = empRepo.findById(dni).orElseThrow();
		emp.setNomEmp(nuevoNombre);
		empRepo.save(emp);
	}
	/// ESTA ANOTACIÓN ARREGLA LA EXCEPCION LAZY INITIALIZATION EXCEPTION
	/// SI NO SE USA ESTO, SE DEBERIA PONER fetchType.EAGER en la relación propiedad
	//@Transactional 
	public void asignarProyecto(String dni, Integer id) {
		Empleado empleado = empRepo.findById(dni).orElseThrow();
		Proyecto proyecto = proyRepo.findById(id).orElseThrow();
		
		//SE HACE DESDE EL PROYECTO PORQUE ES LA CLASE PROYECTO LA PROPIETARIA DE LA RELACION
		//MANYTOMANY
		proyecto.getEmpleados().add(empleado);
		proyRepo.save(proyecto);
	}
	
	

}
