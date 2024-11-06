package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.AlumnoException;
import exception.EmpresaException;
import exception.ExistsException;
import exception.SedeException;
import exception.VoidException;
import repository.Repositorio;

@SuppressWarnings("serial")
public class FrmAsignEmpresaAlum extends JInternalFrame {

	private JLabel lblCodEmpresa;
	private JTextField txtCodEmpresa;
	private JLabel lblCodSede;
	private JTextField txtCodSede;
	private JLabel lblNiaAlum;
	private JTextField txtNiaAlum;
	private JButton btnAsign;
	private JButton btnVolver;

	public FrmAsignEmpresaAlum() {
		super("Asignar empresa a alumno");
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

		lblNiaAlum = new JLabel("Introduce el NIA del alumno:");
		lblNiaAlum.setBounds(31, 138, 223, 14);
		getContentPane().add(lblNiaAlum);

		txtNiaAlum = new JTextField();
		txtNiaAlum.setColumns(10);
		txtNiaAlum.setBounds(31, 158, 156, 20);
		getContentPane().add(txtNiaAlum);

		btnAsign = new JButton("Asignar");
		btnAsign.setBounds(277, 106, 130, 23);
		this.getContentPane().add(btnAsign);
		btnAsign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Repositorio.asignarEmpresaAlumno(Integer.parseInt(txtCodEmpresa.getText()),
							Integer.parseInt(txtCodSede.getText()), txtNiaAlum.getText());
					reset();
					JOptionPane.showMessageDialog(getRootPane(), "Empresa asignada correctamente!");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Algun codigo no tiene el formato esperado!");
				} catch (AlumnoException e2) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El alumno de NIA " + txtNiaAlum.getText() + " no existe!");
				} catch (EmpresaException e3) {
					JOptionPane.showMessageDialog(getRootPane(),
							"La empresa de codigo " + txtCodEmpresa.getText() + " no existe!");
				} catch (SedeException e4) {
					JOptionPane.showMessageDialog(getRootPane(),
							"La sede de codigo " + txtCodSede.getText() + " no existe!");
				} catch (VoidException e5) {
					JOptionPane.showMessageDialog(getRootPane(), e5.getMessage());
				} catch (ExistsException e6) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El alumno de NIA " + txtNiaAlum.getText() + " ya esta aqui!");
				}

			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(300, 219, 107, 23);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);

	}

	protected void reset() {
		txtCodEmpresa.setText("");
		txtCodSede.setText("");
		txtNiaAlum.setText("");
	}

}
