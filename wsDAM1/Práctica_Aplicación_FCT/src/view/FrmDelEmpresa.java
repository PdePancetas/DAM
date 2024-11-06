package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.EmpresaException;
import exception.ExistsException;
import repository.Repositorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FrmDelEmpresa extends JInternalFrame {

	private JTextField txtCodEmpresa;
	private JLabel lblCodEmpresa;
	private JButton btnDel;
	private JButton btnVolver;

	public FrmDelEmpresa() {
		super("Eliminar empresa por código");
		this.getContentPane().setLayout(null);

		lblCodEmpresa = new JLabel("Introduce el código de la empresa:");
		lblCodEmpresa.setBounds(10, 10, 200, 13);
		this.getContentPane().add(lblCodEmpresa);

		txtCodEmpresa = new JTextField();
		txtCodEmpresa.setBounds(20, 33, 96, 19);
		this.getContentPane().add(txtCodEmpresa);
		txtCodEmpresa.setColumns(10);

		btnDel = new JButton("Eliminar");
		btnDel.setBounds(126, 32, 85, 21);
		this.getContentPane().add(btnDel);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (Repositorio.eliminarEmpresa(Integer.parseInt(txtCodEmpresa.getText()))) {
						JOptionPane.showMessageDialog(getRootPane(), "Empresa eliminada!");
					} else
						throw new ExistsException();

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "El codigo no tiene el formato correcto!");
				} catch (EmpresaException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							"La empresa de codigo " + txtCodEmpresa.getText() + " no existe!");
				} catch (ExistsException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							"<html><p>Hay alumnos asignados a esta empresa</p><p>No se puede eliminar!</p></html>");

				}
				reset();
			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(221, 32, 85, 21);
		this.getContentPane().add(btnVolver);
		this.setVisible(false);
	}

	protected void reset() {
		txtCodEmpresa.setText("");
	}
}
