package ejercicios_1_a_8;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana1 extends JFrame {

	JButton eurADol = new JButton("Eur-->Dol");
	JButton dolAEur = new JButton("Dol-->Eur");
	JLabel etiqueta = new JLabel("Introduzca el valor: ");
	JTextField valor = new JTextField(5);
	JButton convertir = new JButton("Convertir");
	JLabel resultado = new JLabel(">Resultado<");

	public Ventana1() {
		super("Conversor de unidades de moneda");
		FlowLayout gestorUbi = new FlowLayout();

		getContentPane().setLayout(gestorUbi);

		getContentPane().add(eurADol);
		getContentPane().add(dolAEur);
		getContentPane().add(etiqueta);
		getContentPane().add(valor);
		getContentPane().add(convertir);
		getContentPane().add(resultado);
		eurADol.addActionListener(new CapturaPulsarOpcionEurADol());
		dolAEur.addActionListener(new CapturaPulsarOpcionDolAEur());
		convertir.addActionListener(new CapturaPulsarCalcular());
	}

	public class CapturaPulsarOpcionEurADol implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			eurADol.setSelected(true);
			dolAEur.setSelected(false);
		}
	}

	public class CapturaPulsarOpcionDolAEur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dolAEur.setSelected(true);
			eurADol.setSelected(false);
		}

	}

	class CapturaPulsarCalcular implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (eurADol.isSelected()) {
				resultado.setText(Double.parseDouble(valor.getText()) * 1.07 + " dolares");
			} else if (dolAEur.isSelected()) {
				resultado.setText(Double.parseDouble(valor.getText()) * 0.93 + " euros");
			}

		}

	}

	public static void main(String[] args) {

		Ventana1 v = new Ventana1();
		v.setSize(800, 500);
		v.setVisible(true);

	}

}
