package view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.EmpresaException;
import exception.ExistsException;
import exception.VoidException;
import repository.Repositorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmAltaSede extends JInternalFrame {

	private JTextField txtCodEmpresa;
	private JLabel lblCodEmpresa;
	private JTextField txtCodSede;
	private JLabel lblCodSede;
	private JLabel lblDirSede;
	private JTextField txtDirSede;
	private JButton btnAddSede;
	private JButton btnVolver;

	public FrmAltaSede() {
		super("Alta Sede");
		getContentPane().setLayout(null);

		lblCodEmpresa = new JLabel("Introduce el c\u00F3digo de la empresa:");
		lblCodEmpresa.setBounds(31, 31, 223, 14);
		this.getContentPane().add(lblCodEmpresa);

		txtCodEmpresa = new JTextField();
		txtCodEmpresa.setBounds(31, 56, 156, 20);
		this.getContentPane().add(txtCodEmpresa);
		txtCodEmpresa.setColumns(10);

		lblCodSede = new JLabel("Introduce el c\u00F3digo de la sede:");
		lblCodSede.setBounds(31, 87, 223, 14);
		this.getContentPane().add(lblCodSede);

		txtCodSede = new JTextField();
		txtCodSede.setBounds(31, 107, 156, 20);
		this.getContentPane().add(txtCodSede);
		txtCodSede.setColumns(10);

		lblDirSede = new JLabel("Introduce la direcci\u00F3n de la sede:");
		lblDirSede.setBounds(31, 138, 223, 14);
		getContentPane().add(lblDirSede);

		txtDirSede = new JTextField();
		txtDirSede.setColumns(10);
		txtDirSede.setBounds(31, 158, 156, 20);
		getContentPane().add(txtDirSede);

		btnAddSede = new JButton("A\u00F1adir Sede");
		btnAddSede.setBounds(277, 106, 130, 23);
		this.getContentPane().add(btnAddSede);
		btnAddSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (Repositorio.altaSede(Integer.parseInt(txtCodEmpresa.getText()),
							Integer.parseInt(txtCodSede.getText()), txtDirSede.getText()))
						JOptionPane.showMessageDialog(getRootPane(), "Sede dada de alta!");
					else
						throw new ExistsException();
					reset();

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Algun codigo no tiene el formato esperado!");
				} catch (ExistsException e2) {
					JOptionPane.showMessageDialog(getRootPane(), "<html><p>La sede con cod " + txtCodSede.getText()
							+ " ya existe</p><p>No se puede volver a añadir</p></html>");
					reset();
				} catch (EmpresaException e3) {
					JOptionPane.showMessageDialog(getRootPane(),
							"La empresa de codigo " + txtCodEmpresa.getText() + " no existe!");
				} catch (VoidException e4) {
					JOptionPane.showMessageDialog(getRootPane(), e4.getMessage());
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
		txtCodSede.setText("");
		txtDirSede.setText("");
	}

}
