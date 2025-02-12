package com.pancetas.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pancetas.main.modelo.Empleado;
import com.pancetas.main.modelo.Proyecto;
import com.pancetas.main.repository.EmpleadoRepository;
import com.pancetas.main.repository.ProyectoRepository;

@Service
public class ProyectoService {

	@Autowired
	EmpleadoRepository empRepo;
	@Autowired
	ProyectoRepository proyRepo;
	
	public void cambiarJefeProyecto(Integer id, String nuevoDni) {
		Proyecto p = proyRepo.findById(id).orElseThrow();
		Empleado emp = empRepo.findById(nuevoDni).orElseThrow();
		p.setEmpleadoJefe(emp);
		proyRepo.save(p);
		//FUERZA LOS CAMBIOS, A VECES PUEDE SER NECESARIO
		proyRepo.flush();
	}
	
}
