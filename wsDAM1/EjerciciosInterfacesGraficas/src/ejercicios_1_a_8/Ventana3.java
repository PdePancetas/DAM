package ejercicios_1_a_8;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana3 extends JFrame {

	/*
	 * Realizar una aplicación para calcular la media de números enteros. Los
	 * números se irán introduciendo en un área de texto, con cada Enter (evento por
	 * defecto del área de texto) se incorporará el nuevo número a la media. No usar
	 * botones, el evento se dispara cuando se pulsa el Enter después de haber
	 * escrito en el área de texto.
	 * 
	 *EXTRA*
	 *Añadir un boton para resetear el ejercicio y poder volver a introducir datos desde cero.
	 */

	JLabel texto = new JLabel("Introduce los valores -> ");
	JTextField dato = new JTextField(10);
	JLabel resultado = new JLabel();
	JButton reset = new JButton("Reset");
	double suma = 0, contador = 0, media;
	ArrayList<String> valoresIntroducidos = new ArrayList<>();

	public Ventana3() {
		super("Calculo de la media");
		FlowLayout gestorUbi = new FlowLayout();
		gestorUbi.setAlignment(0);

		getContentPane().setLayout(gestorUbi);
		getContentPane().add(texto);
		getContentPane().add(dato);
		getContentPane().add(reset);
		getContentPane().add(resultado);
		
		
		
		reset.addActionListener(new PulsarReset());
		dato.addActionListener(new IntroducirTexto());

	}

	class IntroducirTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			suma += (Integer.parseInt(dato.getText()));
			contador++;
			media = suma / contador;
			valoresIntroducidos.add(dato.getText());

			resultado.setText("Valores:" + valoresIntroducidos.toString() + ", Media: " + media);

		}

	}
	
	class PulsarReset implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			suma = 0;
			contador= 0;
			media=0;
			valoresIntroducidos.clear();
			resultado.setText("");
			dato.setText("");
			
		}

	}

	public static void main(String[] args) {

		Ventana3 v = new Ventana3();
		v.setSize(800, 500);
		v.setLocation(100, 200);
		v.setVisible(true);

	}

}
