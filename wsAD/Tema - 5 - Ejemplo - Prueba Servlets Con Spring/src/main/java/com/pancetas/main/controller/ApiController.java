package com.pancetas.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ApiController {

    @GetMapping("/invocarServlet")
    public String invocarServlet(HttpServletRequest request, HttpServletResponse response) {
        
        String mensaje = "Redirigiendo a MiServlet...";
        
        try {
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher("/miServlet");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensaje;
    }
}