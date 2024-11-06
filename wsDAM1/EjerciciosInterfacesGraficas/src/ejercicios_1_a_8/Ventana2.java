package ejercicios_1_a_8;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana2 extends JFrame {

	JButton jugar = new JButton("Jugar");
	JTextField numeros = new JTextField(10);
	JLabel resultado = new JLabel();
	int numero = 0;
	int contador = 0;

	public Ventana2() {
		super("Juego de adivinanza");
		FlowLayout gestorUbi = new FlowLayout();

		getContentPane().setLayout(gestorUbi);

		getContentPane().add(jugar);
		getContentPane().add(numeros);
		getContentPane().add(resultado);
		jugar.addActionListener(new PulsarJugar());
		numeros.addActionListener(new MeterTexto());

	}

	class PulsarJugar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			jugar.setSelected(true);
			jugar.setText("Jugando...");
			if (numero == 0) {
				numero = (int) (Math.random() * 100);
				resultado.setText("");
				contador = 0;
			}
		}

	}

	class MeterTexto implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (jugar.isSelected()) {
				if (Integer.parseInt(numeros.getText()) > numero) {
					resultado.setText("El numero es menor");
					numeros.setText("");
					contador++;
				} else if (Integer.parseInt(numeros.getText()) < numero) {
					resultado.setText("El numero es mayor");
					numeros.setText("");
					contador++;
				} else {
					contador++;
					resultado.setText(
							"Has adivinado el numero, era el " + numero + "!! Te llevo " + contador + " intentos");
					numero = 0;
					numeros.setText("");
					jugar.setText("Jugar");
					contador = 0;
				}

			}

		}

	}

	public static void main(String[] args) {

		Ventana2 v = new Ventana2();
		v.setSize(800, 500);
		v.setVisible(true);

	}

}
