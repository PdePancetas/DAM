package ejercicios_1_a_8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class Ventana4 extends JFrame {

	JLabel resultado = new JLabel("RESULTADO =");
	JLabel texto = new JLabel();
	JTextField operando1 = new JTextField();
	JTextField operando2 = new JTextField();
	JButton sumaBtn = new JButton("+");
	JButton restaBtn = new JButton("-");
	JButton multBtn = new JButton("*");
	JButton divBtn = new JButton("/");

	public Ventana4() {
		super("Calculadora");
		setRootPaneCheckingEnabled(false);
		setSize(new Dimension(501, 400));
		getContentPane().setLayout(null);
		resultado.setBounds(104, 52, 93, 73);
		getContentPane().add(resultado);
		texto.setBounds(237, 52, 93, 73);
		getContentPane().add(texto);
		operando1.setBounds(91, 136, 106, 31);
		getContentPane().add(operando1);
		operando2.setBounds(237, 136, 107, 31);
		getContentPane().add(operando2);
		sumaBtn.setBounds(91, 178, 48, 31);
		getContentPane().add(sumaBtn);
		restaBtn.setBounds(149, 178, 48, 31);
		getContentPane().add(restaBtn);
		multBtn.setBounds(238, 178, 48, 31);
		getContentPane().add(multBtn);
		divBtn.setBounds(296, 178, 48, 31);
		getContentPane().add(divBtn);

		sumaBtn.addActionListener(new Operacion());
		restaBtn.addActionListener(new Operacion());
		multBtn.addActionListener(new Operacion());
		divBtn.addActionListener(new Operacion());

	}

	class Operacion implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Aqui es donde se definen las acciones especificas para cada boton
			if (e.getSource() == sumaBtn) {
				texto.setText(
						(Double.parseDouble(operando1.getText()) + (Double.parseDouble(operando2.getText()))) + "");
			} else if (e.getSource() == restaBtn) {
				texto.setText(
						(Double.parseDouble(operando1.getText()) - (Double.parseDouble(operando2.getText()))) + "");
			} else if (e.getSource() == multBtn) {
				texto.setText(
						(Double.parseDouble(operando1.getText()) * (Double.parseDouble(operando2.getText()))) + "");
			} else if (e.getSource() == divBtn) {
				texto.setText(
						(Double.parseDouble(operando1.getText()) / (Double.parseDouble(operando2.getText()))) + "");
			}

		}
	}

	public static void main(String[] args) {

		final Ventana4 v = new Ventana4();
		SwingUtilities.invokeLater(() -> { // FUNCION LAMBDA PARA COMPACTAR CODIGO
			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(300, 300);
		});

	}

}
