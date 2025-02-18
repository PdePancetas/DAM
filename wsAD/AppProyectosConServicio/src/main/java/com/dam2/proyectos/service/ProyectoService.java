package com.dam2.proyectos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dam2.proyectos.repository.EmpleadoRepository;
import com.dam2.proyectos.repository.ProyectoRepository;

@Service
public class ProyectoService {
	
	@Autowired
    private ProyectoRepository proyRepo;

    @Autowired
    private EmpleadoRepository empRepo;

}
