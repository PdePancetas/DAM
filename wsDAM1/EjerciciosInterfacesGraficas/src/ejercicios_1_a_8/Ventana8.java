package ejercicios_1_a_8;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/*Realizar una aplicación para mantener una agenda de contactos. 
 * Inicialmente estará vacía.
 * Los contactos se almacenan en la aplicación en una estructura HashSet. 
 * La aplicación permitirá añadir contacto, modificar, consultar y eliminar contacto. 
 * Los nombres de los contactos existentes en cada momento se mostrarán en un desplegable JComboBox,
 * que es el componente que se utilizará para seleccionar el contacto con el que operar.
 */

@SuppressWarnings("serial")
public class Ventana8 extends JFrame {

	private JTextField textField;

	JComboBox<String> contactos;
	HashSet<String> listaContactos = new HashSet<String>();
	JLabel textoVariable;
	JLabel texto;
//	JLabel textAreaContactos;
	JButton addContactoBtn;
	JButton modContactoBtn;
	JButton checkContactoBtn;
	JButton delContactoBtn;
	String s1 = "";

	boolean addContactoBtnPulsado = false;
	boolean modContactoBtnPulsado = false;
	boolean checkContactoBtnPulsado = false;
	boolean delContactoBtnPulsado = false;

	public Ventana8() {
		super("Contactos");
		getContentPane().setLayout(null);

		contactos = new JComboBox<>();
		contactos.setBounds(411, 124, 116, 21);
		contactos.addItem("CONTACTOS");
		getContentPane().add(contactos);

		texto = new JLabel("Selecciona uno de los siguientes botones:  ");
		texto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		texto.setBounds(46, 93, 246, 21);
		getContentPane().add(texto);

		textField = new JTextField();
		textField.setBounds(46, 64, 297, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		addContactoBtn = new JButton("A�adir Contacto");
		addContactoBtn.setBounds(46, 124, 202, 21);
		getContentPane().add(addContactoBtn);

		modContactoBtn = new JButton("Modificar Contacto");
		modContactoBtn.setBounds(46, 155, 202, 21);
		getContentPane().add(modContactoBtn);

		checkContactoBtn = new JButton("Consultar Contacto");
		checkContactoBtn.setBounds(46, 186, 202, 21);
		getContentPane().add(checkContactoBtn);

		delContactoBtn = new JButton("Eliminar Contacto");
		delContactoBtn.setBounds(46, 217, 202, 21);
		getContentPane().add(delContactoBtn);

		textoVariable = new JLabel("Esperando input...");
		textoVariable.setBounds(46, 31, 609, 19);
		getContentPane().add(textoVariable);

//		textAreaContactos = new JLabel("");
//		textAreaContactos.setHorizontalAlignment(SwingConstants.LEFT);
//		textAreaContactos.setVerticalAlignment(SwingConstants.TOP);
//		textAreaContactos.setBounds(46, 262, 317, 31);
//		getContentPane().add(textAreaContactos);

		addContactoBtn.addActionListener(new pulsarBotonAñadir());
		modContactoBtn.addActionListener(new pulsarBotonModificar());
		checkContactoBtn.addActionListener(new pulsarBotonConsultar());
		delContactoBtn.addActionListener(new pulsarBotonEliminar());
		contactos.addActionListener(new utilizarComboBox());
		textField.addActionListener(new pulsarEnter());
	}

	class pulsarBotonAñadir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			textField.setEditable(true);

			addContactoBtnPulsado = true;
			modContactoBtnPulsado = false;
			checkContactoBtnPulsado = false;
			delContactoBtnPulsado = false;
			textoVariable.setText("Introduce el contacto a a�adir y pulsa Enter:");

		}
	}

	class pulsarBotonModificar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			textField.setEditable(true);

			addContactoBtnPulsado = false;
			modContactoBtnPulsado = true;
			checkContactoBtnPulsado = false;
			delContactoBtnPulsado = false;
			textoVariable.setText(
					"Selecciona de la lista el contacto a modificar y escribe su nuevo nombre, luego pulsa Enter.");

		}
	}

	class pulsarBotonConsultar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			textField.setEditable(true);

			addContactoBtnPulsado = false;
			modContactoBtnPulsado = false;
			checkContactoBtnPulsado = true;
			delContactoBtnPulsado = false;
			textoVariable.setText("Selecciona de la lista el contacto a consultar.");

		}
	}

	class pulsarBotonEliminar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			textField.setEditable(true);

			addContactoBtnPulsado = false;
			modContactoBtnPulsado = false;
			checkContactoBtnPulsado = false;
			delContactoBtnPulsado = true;
			textoVariable.setText("Escribe a continuacion el contacto a eliminar:");

		}
	}

	class pulsarEnter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (addContactoBtnPulsado) {

				listaContactos.add(textField.getText());
				String[] partes = textField.getText().split(" ");
				if (partes.length > 0)
					contactos.addItem(partes[0]);

				textField.setText("");
//				textAreaContactos.setText(listaContactos.toString());

			} else if (modContactoBtnPulsado) {

				if (!contactos.getSelectedItem().equals("CONTACTOS")) {
					String valor = textField.getText();

					listaContactos.add(valor);
					contactos.addItem(valor.split(" ")[0]);

					if (listaContactos.contains(s1)) {
						listaContactos.remove(s1);
						contactos.removeItem(s1.split(" ")[0]);
					}

					textField.setText("");
//					textAreaContactos.setText(listaContactos.toString());
				} else
					textField.setText("No hay contacto elegido aun");

				textField.setText("");
				contactos.setSelectedIndex(0);
//				textAreaContactos.setText(listaContactos.toString());
				contactos.setSelectedIndex(0);

			} else if (delContactoBtnPulsado) {

				if (listaContactos.contains(textField.getText())) {
					listaContactos.remove(textField.getText());
					contactos.removeItem(textField.getText().split(" ")[0]);
				}

				textField.setText("");
//				textAreaContactos.setText(listaContactos.toString());
				contactos.setSelectedIndex(0);
			}

		}
	}

	class utilizarComboBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (modContactoBtnPulsado) {

				if (!contactos.getSelectedItem().equals("CONTACTOS")) {
					for (String s2 : listaContactos)
						if (s2.split(" ")[0].equals(contactos.getSelectedItem())) {
							textField.setText(s2);
							s1 = textField.getText();
						}

				} else
					textField.setText("Elige un contacto");

			} else if (checkContactoBtnPulsado) {

				if (!contactos.getSelectedItem().equals("CONTACTOS")) {
					for (String s2 : listaContactos)
						if (s2.split(" ")[0].equals(contactos.getSelectedItem())) {
							textField.setText(s2);
							s1 = textField.getText();
						}

				} else
					textField.setText("Elige un contacto");

			}

		}

	}

	public static void main(String[] args) {

		final Ventana8 v = new Ventana8();
		SwingUtilities.invokeLater(() -> { // FUNCION LAMBDA PARA COMPACTAR CODIGO
			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(300, 300);
			v.setSize(700, 400);
		});

	}
}