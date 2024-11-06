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
public class FrmAltaAlumno extends JInternalFrame {

	private JTextField txtNiaAlum;
	private JLabel lblNiaAlum;
	private JTextField txtNameAlum;
	private JLabel lblNameAlum;
	private JButton btnAddAlum;
	private JButton btnVolver;

	public FrmAltaAlumno() {
		super("Alta Alumno");
		getContentPane().setLayout(null);

		lblNiaAlum = new JLabel("Introduce el NIA del alumno:");
		lblNiaAlum.setBounds(31, 31, 223, 14);
		this.getContentPane().add(lblNiaAlum);

		txtNiaAlum = new JTextField();
		txtNiaAlum.setBounds(31, 56, 156, 20);
		this.getContentPane().add(txtNiaAlum);
		txtNiaAlum.setColumns(10);

		lblNameAlum = new JLabel("Introduce el nombre del alumno:");
		lblNameAlum.setBounds(31, 110, 223, 14);
		this.getContentPane().add(lblNameAlum);

		txtNameAlum = new JTextField();
		txtNameAlum.setBounds(31, 135, 156, 20);
		this.getContentPane().add(txtNameAlum);
		txtNameAlum.setColumns(10);

		btnAddAlum = new JButton("A\u00F1adir Alumno");
		btnAddAlum.setBounds(293, 106, 130, 23);
		this.getContentPane().add(btnAddAlum);
		btnAddAlum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (Repositorio.altaAlumno(txtNiaAlum.getText(), txtNameAlum.getText()))
						JOptionPane.showMessageDialog(getRootPane(), "Alumno dado de alta!");
					else
						throw new ExistsException();

					reset();

				} catch (ExistsException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "<html><p>El alumno con NIA " + txtNiaAlum.getText()
							+ " ya existe</p><p>No se puede volver a añadir</p></html>");
					reset();
				} catch (VoidException e2) {
					JOptionPane.showMessageDialog(getRootPane(), e2.getMessage());
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
		txtNameAlum.setText("");
		txtNiaAlum.setText("");
	}

}
