package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.ProfesorException;
import repository.Repositorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FrmViewAlumnosConEmpresaProfesor extends JInternalFrame {

	private JTextField txtNifProf;
	private JLabel lblNifProf;
	private JButton btnShow;
	private JButton btnVolver;
	private JTextArea txtDisplayAlums;

	public FrmViewAlumnosConEmpresaProfesor() {
		super("Mostrar alumnos (con empresa asignada) por profesor");
		this.getContentPane().setLayout(null);

		lblNifProf = new JLabel("Introduce el nif del profesor:");
		lblNifProf.setBounds(10, 34, 172, 13);
		this.getContentPane().add(lblNifProf);

		txtNifProf = new JTextField();
		txtNifProf.setBounds(192, 31, 96, 19);
		this.getContentPane().add(txtNifProf);
		txtNifProf.setColumns(10);

		txtDisplayAlums = new JTextArea();
		txtDisplayAlums.setEditable(false);
		txtDisplayAlums.setText("Aquí se mostrarán los alumnos");
		txtDisplayAlums.setBounds(10, 57, 468, 204);
		this.getContentPane().add(txtDisplayAlums);

		btnShow = new JButton("Mostrar");
		btnShow.setBounds(298, 30, 85, 21);
		this.getContentPane().add(btnShow);
		btnShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					txtDisplayAlums.setText(Repositorio.mostrarAlumnosEmpresasProfesor(txtNifProf.getText()));
				} catch (ProfesorException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El profesor de Nif " + txtNifProf.getText() + " no existe!");
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
		btnVolver.setBounds(393, 30, 85, 21);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);
	}

	protected void reset() {
		txtDisplayAlums.setText("");
		txtNifProf.setText("");
	}
}
