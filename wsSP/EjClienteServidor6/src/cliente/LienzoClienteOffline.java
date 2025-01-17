package cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LienzoClienteOffline extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Point> puntos;
    private Point puntoInicio;

    public List<Point> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<Point> puntos) {
		this.puntos = puntos;
	}

	public Point getPuntoInicio() {
		return puntoInicio;
	}

	public void setPuntoInicio(Point puntoInicio) {
		this.puntoInicio = puntoInicio;
	}

	public LienzoClienteOffline() {
        puntos = new ArrayList<>();
        puntoInicio = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (puntoInicio == null) {
                    puntoInicio = e.getPoint();
                } else {
                    puntos.add(puntoInicio);
                    puntos.add(e.getPoint());
                    puntoInicio = null;
                    repaint(); // Redibuja el lienzo
                }
            }
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Para mejor calidad de dibujo

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Antialiasing

        g2d.setColor(Color.RED); // Color de las líneas

        for (int i = 0; i < puntos.size(); i += 2) {
            Point p1 = puntos.get(i);
            Point p2 = puntos.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        if (puntoInicio!=null){
            g2d.setColor(Color.RED);
            g2d.fillOval(puntoInicio.x-3, puntoInicio.y-3, 6, 6);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lienzo de Dibujo");
            LienzoClienteOffline lienzo = new LienzoClienteOffline();
            lienzo.setPreferredSize(new Dimension(500, 400)); // Tamaño del lienzo
            frame.add(lienzo);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}