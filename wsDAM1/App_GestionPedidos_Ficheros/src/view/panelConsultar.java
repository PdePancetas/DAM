package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Pedido;
import model.Producto;
import repository.Ops;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;

public class panelConsultar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2447675248061153319L;
	private JTextField txtCliente;
	private JTextArea txtAreaPedidos;
	private JButton btnConsultar;
	private JTextField txtProducto;

	public panelConsultar() {
		setLayout(null);

		JLabel lblNombreCliente = new JLabel("NOMBRE CLIENTE:");
		lblNombreCliente.setBounds(23, 11, 140, 14);
		add(lblNombreCliente);

		txtCliente = new JTextField();
		txtCliente.setBounds(23, 36, 140, 20);
		add(txtCliente);
		txtCliente.setColumns(10);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtCliente.getText().equals("")&&txtProducto.getText().equals("")) {
					try {
						HashMap<Pedido, Double> pedidos = Ops.getPedidoYPrecio(Ventana.f, txtCliente.getText());
						String s = "";
						Set<Entry<Pedido, Double>> pedidoSet = pedidos.entrySet();
						for (Entry<Pedido, Double> entry : pedidoSet) {
							s += "Codigo: "+entry.getKey().getCodigo() + ", Precio Pedido: " + entry.getValue() + " euros\n";
						}
						s += "\nPrecio total de todos los pedidos: " + Ops.precioTotalPedidos(pedidos) + " euros";
						txtAreaPedidos.setText(s);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				} else if (!txtProducto.getText().equals("")&&!txtCliente.getText().equals("")) {
					try {
						ArrayList<Pedido> pedidos = Ops.getPedidosPorProductoYCliente(Ventana.f, txtCliente.getText(),
								txtProducto.getText());
						String s = "";
						for (Pedido p : pedidos)
							s += "Codigo: "+p.getCodigo() + ", Precio Pedido: " + Ops.precioTotalPedido(p) + "\n";

						txtAreaPedidos.setText(s);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}

				}else if(!txtProducto.getText().equals("")&&txtCliente.getText().equals("")) {
					try {
						HashMap<Pedido, Double> pedidos = Ops.getPedidosPorProducto(Ventana.f, txtProducto.getText());
						String s = "";
						Set<Entry<Pedido, Double>> pedidoSet = pedidos.entrySet();
						for (Entry<Pedido, Double> entry : pedidoSet) {
							s += "Codigo"+entry.getKey().getCodigo() + ", Cliente: "+entry.getKey().getNombreCliente()+", Precio Pedido: " + entry.getValue() + " euros\n";
						}
						txtAreaPedidos.setText(s);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}else if(txtCliente.getText().equals("")&&txtProducto.getText().equals("")) {
					JOptionPane.showMessageDialog(getRootPane(), "El campo cliente o el campo producto deben estar rellenos");
				}

			}
		});
		btnConsultar.setBounds(258, 35, 89, 23);
		add(btnConsultar);

		JLabel lblResultados = new JLabel("RESULTADOS:");
		lblResultados.setBounds(23, 80, 89, 14);
		add(lblResultados);

		txtAreaPedidos = new JTextArea();
		txtAreaPedidos.setEditable(false);
		txtAreaPedidos.setBounds(158, 75, 330, 256);
		add(txtAreaPedidos);

		JLabel lblNombreProducto = new JLabel("NOMBRE PRODUCTO:");
		lblNombreProducto.setBounds(406, 11, 140, 14);
		add(lblNombreProducto);

		txtProducto = new JTextField();
		txtProducto.setColumns(10);
		txtProducto.setBounds(406, 36, 140, 20);
		add(txtProducto);

	}

}
