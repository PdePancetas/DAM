package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.ExistsException;
import exception.VoidException;
import repository.Repositorio;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmAltaProfesor extends JInternalFrame {

	private JButton btnAddProf;
	private JTextField txtNifProf;
	private JLabel lblNifProf;
	private JTextField txtNameProf;
	private JLabel lblNameProf;
	private JButton btnVolver;

	public FrmAltaProfesor() {
		super("Alta Profesor");
		getContentPane().setLayout(null);

		lblNifProf = new JLabel("Introduce el nif del profesor:");
		lblNifProf.setBounds(31, 31, 223, 14);
		this.getContentPane().add(lblNifProf);

		txtNifProf = new JTextField();
		txtNifProf.setBounds(31, 56, 156, 20);
		this.getContentPane().add(txtNifProf);
		txtNifProf.setColumns(10);

		lblNameProf = new JLabel("Introduce el nombre del profesor");
		lblNameProf.setBounds(31, 110, 223, 14);
		this.getContentPane().add(lblNameProf);

		txtNameProf = new JTextField();
		txtNameProf.setBounds(31, 135, 156, 20);
		this.getContentPane().add(txtNameProf);
		txtNameProf.setColumns(10);

		btnAddProf = new JButton("A\u00F1adir Profesor");
		btnAddProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (Repositorio.altaProfesor(txtNifProf.getText(), txtNameProf.getText()))
						JOptionPane.showMessageDialog(getRootPane(), "Profesor dado de alta!");
					else
						throw new ExistsException();
					reset();

				} catch (ExistsException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "<html><p>El profesor con nif " + txtNifProf.getText()
							+ " ya existe</p><p>No se puede volver a añadir</p></html>");
					reset();
				} catch (VoidException e2) {
					JOptionPane.showMessageDialog(getRootPane(), e2.getMessage());
				}

			}
		});
		btnAddProf.setBounds(293, 106, 130, 23);
		this.getContentPane().add(btnAddProf);

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

	private void reset() {
		txtNameProf.setText("");
		txtNifProf.setText("");
	}

}
