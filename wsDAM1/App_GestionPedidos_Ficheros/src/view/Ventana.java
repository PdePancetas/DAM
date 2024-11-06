package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	public static File f = new File(".\\Pedidos\\Pedidos.txt");

	private JPanel panelConsultas;
	private JPanel panelInsertar;

	/*
	 * 2. Realizar una aplicaci�n de gesti�n de pedidos. Los pedidos tendr�n como
	 * propiedades c�digo, nombre del cliente y lista de productos. Cada producto a
	 * su vez se caracteriza por su nombre y su precio. Los pedidos se almacenar�n
	 * en un fichero de objetos serializados. La comunicaci�n con el usuario ser�
	 * con interfaz gr�fica. Las funcionalidades ser�n: 
	 * 
	 * - Insertar pedido -
	 * Consultar pedidos de un cliente, calculando su precio total 
	 * 
	 * - Buscar pedidos
	 * en los que se encuentre un producto. Si en el panel de consultas se deja el
	 * campo producto vacio, mostrar todos los pedidos de ese cliente en el area de
	 * texto con su precio total
	 */

	// MIRAR EL METODO INSERTAR PEDIDO

	public Ventana() {
		super("Gestion de Pedidos");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (f.length() > 0) {
					int op = JOptionPane.showConfirmDialog(rootPane, "Desea borrar los pedidos realizados?");
					if (op == JOptionPane.YES_OPTION)
						f.delete();
				}
			}
		});
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 670, 436);
		panelInsertar = new panelInsertar();
		tabbedPane.addTab("Insertar", panelInsertar);
		panelConsultas = new panelConsultar();
		tabbedPane.addTab("Consultas", panelConsultas);
		getContentPane().add(tabbedPane);

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		final Ventana v = new Ventana();
		SwingUtilities.invokeLater(() -> {

			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(200, 200);
			v.setSize(710, 500);
			v.setResizable(false);

		});

//		ObjectInputStream ois = new Object_Input_StreamSinHeader3(new FileInputStream(f));
//		boolean finalArchivo = false;
//		while (!finalArchivo)
//			try {
//				Pedido p = (Pedido) ois.readObject();
//				if (p.getCodigo() == 0) {
//					System.out.println(p.getCodigo() + "-" + p.getNombreCliente());
//				}
//			} catch (EOFException e) {
//				finalArchivo = true;
//			}
//
//		ois.close();

	}
}