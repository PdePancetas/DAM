package com.pancetas.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.pancetas.main.servlets.MiServlet;

@SpringBootApplication
public class PruebaServletsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaServletsApplication.class, args);
	}
	
	
	// Registro del servlet
    @Bean
    public ServletRegistrationBean<MiServlet> servletRegistrationBean() {
        return new ServletRegistrationBean<>(new MiServlet(), "/miServlet");
    }

}
