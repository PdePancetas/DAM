package vista;

import java.awt.FlowLayout;

import javax.swing.*;

import logic.PulsarBoton;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	public Ventana(){
		
		this.getContentPane().setLayout(new FlowLayout());
		
		JTextField operando1 = new JTextField(5);
		getContentPane().add(operando1);
		
		JButton boton = new JButton("+");
		getContentPane().add(boton);
		
		JTextField operando2 = new JTextField(5);
		getContentPane().add(operando2);
		
		JLabel simbolo = new JLabel(" = ");
		getContentPane().add(simbolo);
		
		JLabel resultado = new JLabel();
		getContentPane().add(resultado);
		
		boton.addActionListener(new PulsarBoton(operando1, operando2, resultado));
		
	}
	
	
	
	
	
}
