package view;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import repository.Repositorio;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FrmProfesorConMasAlumnosEmpresa extends JInternalFrame {

	private JButton btnMostrarAlumnos;
	private JButton btnVolver;

	public FrmProfesorConMasAlumnosEmpresa() {
		super("Profesor con mas alumnos en empresas");
		this.getContentPane().setLayout(null);

		btnMostrarAlumnos = new JButton("Mostrar");
		btnMostrarAlumnos.setBounds(10, 10, 84, 68);
		btnMostrarAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.getContentPane().add(btnMostrarAlumnos);
		btnMostrarAlumnos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(getRootPane(), "El profesor con mas alumnos asignados en empresas es: "
						+ Repositorio.profesorConMasAlumnosEmpresa());

			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(141, 33, 107, 23);
		this.getContentPane().add(btnVolver);
	}

}
