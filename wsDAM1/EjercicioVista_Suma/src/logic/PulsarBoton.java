package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PulsarBoton implements ActionListener {

	JTextField operando1, operando2;
	JLabel resultado;

	public PulsarBoton(JTextField operando1, JTextField operando2, JLabel resultado) {
		this.operando1 = operando1;
		this.operando2 = operando2;
		this.resultado = resultado;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			resultado.setText((Integer.parseInt(operando1.getText())+Integer.parseInt(operando2.getText()))+"");
		} catch (NumberFormatException e1) {
			
			resultado.setText("Algun operando no es un numero valido");
		}
		
		
	}

}
