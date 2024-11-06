package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.VoidException;
import repository.Ops;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class panelInsertar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2447675248061153319L;
	private JTextField txtCliente;
	private JTextArea txtAreaProductos;
	private JButton btnInsertar;
	int cod = 0;

	public panelInsertar() {
		setLayout(null);

		JLabel lblNombreCliente = new JLabel("NOMBRE CLIENTE:");
		lblNombreCliente.setBounds(23, 11, 140, 14);
		add(lblNombreCliente);

		txtCliente = new JTextField();
		txtCliente.setBounds(23, 36, 140, 20);
		add(txtCliente);
		txtCliente.setColumns(10);

		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ops.insertarPedido(cod, txtCliente.getText(), txtAreaProductos.getText(), Ventana.f);
					JOptionPane.showMessageDialog(getRootPane(), "Pedido añadido!");
					reset();
					cod++;
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (VoidException e1) {
					JOptionPane.showMessageDialog(getRootPane(), "Algun campo esta vacio!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getRootPane(), "No esta bien puesto el formato de los productos!");
				}
			}
		});
		btnInsertar.setBounds(258, 35, 89, 23);
		add(btnInsertar);

		JLabel lblNewLabel = new JLabel("PRODUCTOS:");
		lblNewLabel.setBounds(23, 80, 89, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("(Producto - Precio)");
		lblNewLabel_1.setBounds(23, 105, 108, 23);
		add(lblNewLabel_1);

		txtAreaProductos = new JTextArea();
		txtAreaProductos.setBounds(158, 75, 330, 256);
		add(txtAreaProductos);

	}

	protected void reset() {
		txtAreaProductos.setText("");
		txtCliente.setText("");

	}

}
