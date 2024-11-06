package view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.EmpresaException;
import exception.ExistsException;
import exception.SedeException;
import exception.VoidException;
import repository.Repositorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmCambiarAlumSede extends JInternalFrame {

	private JTextField txtCodEmpresa;
	private JLabel lblCodEmpresa;
	private JTextField txtCodSedeAntigua;
	private JLabel lblCodSedeAntigua;
	private JLabel lblCodSedeNueva;
	private JTextField txtCodSedeNueva;
	private JButton btnCambiarSede;
	private JButton btnVolver;

	public FrmCambiarAlumSede() {
		super("Cambiar de Sede Alumnos ");
		getContentPane().setLayout(null);

		lblCodEmpresa = new JLabel("Introduce el c\u00F3digo de la empresa:");
		lblCodEmpresa.setBounds(23, 106, 223, 14);
		this.getContentPane().add(lblCodEmpresa);

		txtCodEmpresa = new JTextField();
		txtCodEmpresa.setBounds(23, 130, 156, 20);
		this.getContentPane().add(txtCodEmpresa);
		txtCodEmpresa.setColumns(10);

		lblCodSedeAntigua = new JLabel("Introduce el código de la sede origen:");
		lblCodSedeAntigua.setBounds(264, 44, 223, 14);
		this.getContentPane().add(lblCodSedeAntigua);

		txtCodSedeAntigua = new JTextField();
		txtCodSedeAntigua.setBounds(264, 68, 156, 20);
		this.getContentPane().add(txtCodSedeAntigua);
		txtCodSedeAntigua.setColumns(10);

		lblCodSedeNueva = new JLabel("Introduce el código de la sede destino:");
		lblCodSedeNueva.setBounds(264, 139, 223, 14);
		getContentPane().add(lblCodSedeNueva);

		txtCodSedeNueva = new JTextField();
		txtCodSedeNueva.setColumns(10);
		txtCodSedeNueva.setBounds(264, 163, 156, 20);
		getContentPane().add(txtCodSedeNueva);

		btnCambiarSede = new JButton("Efectuar Cambio");
		btnCambiarSede.setBounds(277, 106, 130, 23);
		this.getContentPane().add(btnCambiarSede);
		btnCambiarSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Repositorio.cambiarAlumnosSedeEmpresa(Integer.parseInt(txtCodEmpresa.getText()),
							Integer.parseInt(txtCodSedeAntigua.getText()), Integer.parseInt(txtCodSedeNueva.getText()));
					JOptionPane.showMessageDialog(getRootPane(), "Alumnos cambiado de sede "
							+ txtCodSedeAntigua.getText() + " a sede " + txtCodSedeNueva.getText() + ".");
					reset();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "El codigo no tiene el formato correcto!");
				} catch (EmpresaException e2) {
					JOptionPane.showMessageDialog(getRootPane(),
							"La empresa de codigo " + txtCodEmpresa.getText() + " no existe!");
				} catch (SedeException e3) {
					JOptionPane.showMessageDialog(getRootPane(), "La sede de codigo " + txtCodSedeAntigua.getText()
							+ " o codigo" + txtCodSedeNueva.getText() + "no existe!");
				} catch (VoidException e4) {
					JOptionPane.showMessageDialog(getRootPane(), e4.getMessage());
				} catch (ExistsException e5) {
					JOptionPane.showMessageDialog(getRootPane(), "No hay alumnos asignados en esta sede");
				}

			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				reset();
			}
		});
		btnVolver.setBounds(313, 220, 107, 23);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);

	}

	protected void reset() {
		txtCodEmpresa.setText("");
		txtCodSedeAntigua.setText("");
		txtCodSedeNueva.setText("");
	}

}
