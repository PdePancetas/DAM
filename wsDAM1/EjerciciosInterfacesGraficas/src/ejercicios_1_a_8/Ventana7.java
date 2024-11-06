package ejercicios_1_a_8;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class Ventana7 extends JFrame {

	JLabel etiqueta;
	int xFin;
	int yFin;
	int cont = 0;
	public Ventana7() {
		super("Etiqueta Troll");
		getContentPane().setLayout(null);

		etiqueta = new JLabel("Atrapame!");

		etiqueta.setFont(new Font("Verdana", Font.PLAIN, 10));
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
		etiqueta.setBounds(171, 98, 150, 33);
		getContentPane().add(etiqueta);

		etiqueta.addMouseListener(new MouseHover());
	}

	class MouseHover implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent e) {
			
			if (cont == 0) {
				xFin = (int) (Math.random() * ((getWidth() - 150) - etiqueta.getWidth()));
				yFin = (int) (Math.random() * ((getHeight() - 33) - etiqueta.getHeight()));
				System.out.println(xFin + ":" + yFin);
				cont++;
			}
			
			boolean acabado = false;
			if (!acabado)
				etiqueta.setLocation((int) (Math.random() * (getWidth() - 150)),
						(int) (Math.random() * ((getHeight() - 33) - etiqueta.getHeight())));

			if (etiqueta.getLocation().x == xFin && etiqueta.getLocation().y == yFin) {
				etiqueta.setText("El juego ha terminado.");
				acabado = true;
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

	}

	public static void main(String[] args) {

		final Ventana7 v = new Ventana7();
		SwingUtilities.invokeLater(() -> { // FUNCION LAMBDA PARA COMPACTAR CODIGO
			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(300, 300);
			v.setSize(350, 350);
		});

	}
}
