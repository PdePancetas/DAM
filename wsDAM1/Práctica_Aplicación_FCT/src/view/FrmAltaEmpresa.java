package view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.ExistsException;
import exception.VoidException;
import repository.Repositorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmAltaEmpresa extends JInternalFrame {

	private JTextField txtCodEmpresa;
	private JLabel lblCodEmpresa;
	private JTextField txtNameEmpresa;
	private JLabel lblNameEmpresa;
	private JButton btnAddEmpresa;
	private JButton btnVolver;

	public FrmAltaEmpresa() {
		super("Alta Empresa");
		getContentPane().setLayout(null);

		lblCodEmpresa = new JLabel("Introduce el c\u00F3digo de la empresa:");
		lblCodEmpresa.setBounds(31, 31, 223, 14);
		this.getContentPane().add(lblCodEmpresa);

		txtCodEmpresa = new JTextField();
		txtCodEmpresa.setBounds(31, 56, 156, 20);
		this.getContentPane().add(txtCodEmpresa);
		txtCodEmpresa.setColumns(10);

		lblNameEmpresa = new JLabel("Introduce el nombre de la empresa:");
		lblNameEmpresa.setBounds(31, 110, 223, 14);
		this.getContentPane().add(lblNameEmpresa);

		txtNameEmpresa = new JTextField();
		txtNameEmpresa.setBounds(31, 135, 156, 20);
		this.getContentPane().add(txtNameEmpresa);
		txtNameEmpresa.setColumns(10);

		btnAddEmpresa = new JButton("A\u00F1adir Empresa");
		btnAddEmpresa.setBounds(293, 106, 130, 23);
		this.getContentPane().add(btnAddEmpresa);
		btnAddEmpresa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (Repositorio.altaEmpresa(Integer.parseInt(txtCodEmpresa.getText()), txtNameEmpresa.getText()))
						JOptionPane.showMessageDialog(getRootPane(), "Empresa dada de alta!");
					else
						throw new ExistsException();
					reset();

				} catch (ExistsException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "<html><p>La empresa con codigo "
							+ txtCodEmpresa.getText() + " ya existe.</p><p>No se puede volver a añadir.</p></html>");
					reset();
				} catch (VoidException e2) {
					JOptionPane.showMessageDialog(getRootPane(), e2.getMessage());
				} catch (NumberFormatException e3) {
					JOptionPane.showMessageDialog(getRootPane(), "El codigo no tiene el formato correcto!");
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
		btnVolver.setBounds(300, 219, 107, 23);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);

	}

	protected void reset() {
		txtCodEmpresa.setText("");
		txtNameEmpresa.setText("");
	}

}
