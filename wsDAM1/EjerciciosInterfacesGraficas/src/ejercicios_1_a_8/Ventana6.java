package ejercicios_1_a_8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana6 extends JFrame {

	JFileChooser selecArchivo = new JFileChooser();
	JLabel instruccion;
	JTextField pathDisplay;
	JButton elegirArchivoBtn;

	public Ventana6() {
		super("Lista de Peliculas");
		getContentPane().setLayout(null);

		instruccion = new JLabel("Pulsa en el boton y elige una ruta:");
		instruccion.setBounds(85, 81, 222, 14);
		getContentPane().add(instruccion);

		pathDisplay = new JTextField();
		pathDisplay.setEditable(false);
		pathDisplay.setBounds(85, 116, 416, 20);
		getContentPane().add(pathDisplay);
		pathDisplay.setColumns(10);

		elegirArchivoBtn = new JButton("...");
		elegirArchivoBtn.setBounds(587, 115, 37, 23);
		getContentPane().add(elegirArchivoBtn);

		elegirArchivoBtn.addActionListener(new PulsarBtn());

	}

	class PulsarBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				selecArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int seleccion = selecArchivo.showOpenDialog(elegirArchivoBtn);
				if ((seleccion == JFileChooser.APPROVE_OPTION) & (selecArchivo.getSelectedFile().getName().endsWith(".txt")))
					pathDisplay.setText(selecArchivo.getSelectedFile().getAbsolutePath());
				else
					pathDisplay.setText("Debes seleccionar un archivo de extension .txt");
				
			} catch (InvalidPathException e1) {
				pathDisplay.setText("Ruta del archivo no valida");
			} catch (NullPointerException e2) {
				pathDisplay.setText("Selecciona un archivo valido");
			}

		}

	}

	public static void main(String[] args) {

		final Ventana6 v = new Ventana6();
		SwingUtilities.invokeLater(() -> { // FUNCION LAMBDA PARA COMPACTAR CODIGO
			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(200, 200);
			v.setSize(800, 300);
		});

	}

}
