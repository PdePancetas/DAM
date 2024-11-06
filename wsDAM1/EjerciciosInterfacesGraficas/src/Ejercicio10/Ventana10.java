package Ejercicio10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;
import java.awt.Font;

/*
 * 10.	Con un diseño a elegir, realizar una aplicación con interfaz gráfica para gestionar pedidos. 
 * Los pedidos se guardarán en la aplicación en un Map donde la clave será el código único 
 * y el valor un objeto Pedido con propiedades tipo, descripción y precio. 
 * La variable tipo será de tipo enumerado, con tres posibles valores: particular, empresa, administración.
 * Las funcionalidades de la aplicación serán: insertar pedido, consultar pedido, modificar pedido. 
 * La interfaz podría ser como la que acompaña.
 * Insertar pedido: inserta en el Map el par correspondiente a los datos que se adjuntan
 * Consultar pedido: muestra en las áreas de texto el pedido cuyo código se indique en el área Código
 * Modificar pedido: modifica el pedido cuyo código se indica en el área Código con los datos que se adjuntan
 * Eliminar pedido: elimina el pedido cuyo código se indica en el área Código.
 * 
 */

@SuppressWarnings("serial")
public class Ventana10 extends JFrame {

	
	private JTextField codigoField;
	private JComboBox<String> tipoBox;
	private JTextField descField;
	private JTextField precioField;
	private JLabel lblCodigo;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblPrecio;
	private JButton addPedidoBtn;
	private JButton checkPedidoBtn;
	private JButton modPedidoBtn;
	private JButton delPedidoBtn;
	private JButton accionBtn;
	private JLabel textoVariable;
	private JButton accionBtnGuardar;
	private JLabel lblCodigosDePedidos;
	private JTextArea pedidosMostrados;

	public Ventana10() {
		super("Gestión de Pedidos 2.0");
		getContentPane().setLayout(null);

		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(40, 45, 70, 20);
		getContentPane().add(lblCodigo);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(40, 112, 75, 20);
		getContentPane().add(lblDescripcion);

		lblTipo = new JLabel("<html><p>Tipo:</p><p>1. Particular</p><p>2. Empresa</p><p>3. Administración</p></html>");
		lblTipo.setBounds(258, 25, 110, 59);
		getContentPane().add(lblTipo);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(279, 112, 70, 20);
		getContentPane().add(lblPrecio);

		codigoField = new JTextField();
		codigoField.setEditable(false);
		codigoField.setBounds(120, 45, 110, 20);
		getContentPane().add(codigoField);
		codigoField.setColumns(10);

		tipoBox = new JComboBox<String>();
		tipoBox.setEnabled(false);
		tipoBox.setBounds(376, 45, 110, 20);
		tipoBox.addItem("Opciones");
		tipoBox.insertItemAt("1", 1);
		tipoBox.insertItemAt("2", 2);
		tipoBox.insertItemAt("3", 3);
		getContentPane().add(tipoBox);

		descField = new JTextField();
		descField.setEditable(false);
		descField.setColumns(10);
		descField.setBounds(120, 112, 110, 20);
		getContentPane().add(descField);

		precioField = new JTextField();
		precioField.setEditable(false);
		precioField.setDragEnabled(true);
		precioField.setColumns(10);
		precioField.setBounds(376, 112, 110, 20);
		getContentPane().add(precioField);

		addPedidoBtn = new JButton("Añadir Pedido");
		addPedidoBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 11));
		addPedidoBtn.setBounds(56, 205, 150, 50);
		getContentPane().add(addPedidoBtn);

		checkPedidoBtn = new JButton("ConsultarPedido");
		checkPedidoBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 11));
		checkPedidoBtn.setBounds(216, 205, 150, 50);
		getContentPane().add(checkPedidoBtn);

		modPedidoBtn = new JButton("Modificar Pedido");
		modPedidoBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 11));
		modPedidoBtn.setBounds(376, 205, 150, 50);
		getContentPane().add(modPedidoBtn);

		delPedidoBtn = new JButton("Eliminar Pedido");
		delPedidoBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 11));
		delPedidoBtn.setBounds(536, 205, 150, 50);
		getContentPane().add(delPedidoBtn);

		accionBtn = new JButton("");
		accionBtn.setVisible(false);
		accionBtn.setBounds(558, 34, 150, 50);
		getContentPane().add(accionBtn);

		textoVariable = new JLabel("Pulsa uno de los siguientes botones:");
		textoVariable.setBounds(56, 174, 434, 20);
		getContentPane().add(textoVariable);

		accionBtnGuardar = new JButton("Guardar");
		accionBtnGuardar.setVisible(false);
		accionBtnGuardar.setBounds(558, 113, 150, 50);
		getContentPane().add(accionBtnGuardar);

		pedidosMostrados = new JTextArea();
		pedidosMostrados.setLineWrap(true);
		pedidosMostrados.setBounds(56, 285, 630, 78);
		getContentPane().add(pedidosMostrados);

		lblCodigosDePedidos = new JLabel("Códigos de pedidos:");
		lblCodigosDePedidos.setBounds(56, 255, 434, 20);
		getContentPane().add(lblCodigosDePedidos);

		addPedidoBtn.addActionListener(new AddPedidoBtn());
		checkPedidoBtn.addActionListener(new CheckPedidoBtn());
		modPedidoBtn.addActionListener(new ModPedidoBtn());
		delPedidoBtn.addActionListener(new DelPedidoBtn());
		accionBtn.addActionListener(new AccionBtn());
		accionBtnGuardar.addActionListener(new AccionBtnGuardar());

	}

	class AddPedidoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			codigoField.setEditable(true);
			tipoBox.setEnabled(true);
			descField.setEditable(true);
			precioField.setEditable(true);
			accionBtn.setVisible(true);
			accionBtn.setEnabled(true);
			accionBtnGuardar.setVisible(false);
			resetTexto();
			tipoBox.setSelectedItem("Opciones");

			textoVariable.setText("Introduce los valores del producto y pulsa Añadir");
			accionBtn.setText("Añadir");

		}
	}

	class CheckPedidoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			codigoField.setEditable(true);
			tipoBox.setEnabled(false);
			descField.setEditable(false);
			precioField.setEditable(false);
			accionBtn.setVisible(true);
			accionBtn.setEnabled(true);
			accionBtnGuardar.setVisible(false);
			resetTexto();
			tipoBox.setSelectedItem("Opciones");

			textoVariable.setText("Introduce el codigo del producto y pulsa en Mostrar");
			accionBtn.setText("Mostrar");

		}
	}

	class ModPedidoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			codigoField.setEditable(true);
			tipoBox.setEnabled(true);
			descField.setEditable(true);
			precioField.setEditable(true);
			accionBtn.setVisible(true);
			accionBtn.setEnabled(true);
			accionBtnGuardar.setVisible(true);
			resetTexto();
			tipoBox.setSelectedItem("Opciones");

			textoVariable
					.setText("Busca tu pedido por codigo (Mostrar), cambia los valores que quieras y pulsa en Guardar");
			accionBtn.setText("Mostrar");

		}
	}

	class DelPedidoBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			codigoField.setEditable(true);
			tipoBox.setEnabled(false);
			descField.setEditable(false);
			precioField.setEditable(false);
			accionBtn.setVisible(true);
			accionBtn.setEnabled(true);
			accionBtnGuardar.setVisible(false);
			resetTexto();
			tipoBox.setSelectedItem("Opciones");

			textoVariable.setText("Introduce el codigo del producto y pulsa Eliminar");
			accionBtn.setText("Eliminar");

		}
	}

	class AccionBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (accionBtn.getText().equals("Añadir")) {
				if (codigoField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debes introducir un codigo!!");
					codigoField.setText("");
				} else if (tipoBox.getSelectedItem().equals("Opciones"))
					JOptionPane.showMessageDialog(null, "Aun no has introducido el tipo de pedido");
				else
					try {
						Pedido2.addPedido(codigoField.getText(), new Pedido2(tipoBox.getSelectedIndex(),
								descField.getText(), Double.parseDouble(precioField.getText())));
						resetTexto();
						tipoBox.setSelectedItem("Opciones");
						mostrarPedidos();
						JOptionPane.showMessageDialog(null, "Pedido añadido!");

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Has introducido algun campo con un formato erroneo");
					} catch (NullPointerException e2) {
						JOptionPane.showMessageDialog(null, "Has introducido algun campo con un formato erroneo");
					} catch (NegativeNumberException e3) {
						JOptionPane.showMessageDialog(null, "No se puede añadir un pedido con precio negativo!");
					}

			} else if (accionBtn.getText().equals("Mostrar")) {
				if (codigoField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debes introducir un codigo!!");
					codigoField.setText("");
				} else
					try {
						Pedido2 p = Pedido2.checkPedido(codigoField.getText());
						tipoBox.setSelectedIndex(p.getTipo());
						descField.setText(p.getDescripcion());
						precioField.setText(p.getPrecio() + "");
						codigoField.setEditable(false);
						JOptionPane.showMessageDialog(null, "Pedido encontrado!");
					} catch (Exception e1) {

					}

			} else if (accionBtn.getText().equals("Eliminar")) {
				if (!Pedido2.delPedido(codigoField.getText())) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado el pedido!");
				} else {
					resetTexto();
					mostrarPedidos();
					JOptionPane.showMessageDialog(null, "Pedido eliminado!");
				}
				
			} else {

			}

		}
	}

	class AccionBtnGuardar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (accionBtnGuardar.getText().equals("Guardar"))
				if (!tipoBox.getSelectedItem().equals("Opciones")) {
					HashMap<String, Pedido2> pedidos2 = Pedido2.getPedidos2(); 
					pedidos2.get(codigoField.getText()).setTipo(tipoBox.getSelectedIndex());
					pedidos2.get(codigoField.getText()).setDescripcion(descField.getText());
					pedidos2.get(codigoField.getText()).setPrecio(Double.parseDouble(precioField.getText()));
					resetTexto();
					tipoBox.setSelectedItem("Opciones");

					System.out.println(pedidos2.get(codigoField.getText()));

					codigoField.setEditable(true);
					mostrarPedidos();
					JOptionPane.showMessageDialog(null, "Pedido Actualizado!");
				} else
					JOptionPane.showMessageDialog(null, "Aún no has introducido el tipo de pedido");

		}
	}

	public static void main(String[] args) {

		final Ventana10 v = new Ventana10();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				v.setVisible(true);
				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				v.setLocation(300, 300);
				v.setSize(800, 400);
			}

		});

	}

	public void resetTexto() {
		codigoField.setText("");
		descField.setText("");
		precioField.setText("");
	}

	public void mostrarPedidos() {
		pedidosMostrados.setText("");
		Set<String> pedidosSet = Pedido2.getPedidos2().keySet();
		String codigos = "[ ";
		for (String s : pedidosSet) {
			codigos += s + ", ";
		}
		codigos += "]";
		pedidosMostrados.setText(codigos);

	}
}