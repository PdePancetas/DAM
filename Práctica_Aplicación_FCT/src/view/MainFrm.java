package view;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

public class MainFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9012707258549540220L;
	private static JDesktopPane escritorio;
	private static JComboBox<String> comboBox;
	private static JMenuBar menuBar;
	private static ArrayList<JInternalFrame> frames = new ArrayList<>();

	private static FrmAltaProfesor frmAltaProfesor = new FrmAltaProfesor();
	private static FrmAltaAlumno frmAltaAlumno = new FrmAltaAlumno();
	private static FrmAltaEmpresa frmAltaEmpresa = new FrmAltaEmpresa();
	private static FrmAltaSede frmAltaSede = new FrmAltaSede();
	private static FrmAsignEmpresaAlum asignEmpresaAlum = new FrmAsignEmpresaAlum();
	private static FrmAsignAlumProf asignAlumProf = new FrmAsignAlumProf();
	private static FrmViewAlumnosConEmpresaProfesor mostrarAlumnosConEmpresaProfesor = new FrmViewAlumnosConEmpresaProfesor();
	private static FrmMostrarAlumnosSinEmpresa mostrarAlumnosSinEmpresa = new FrmMostrarAlumnosSinEmpresa();
	private static FrmProfesorConMasAlumnosEmpresa profesorConMasAlumnosEmpresa = new FrmProfesorConMasAlumnosEmpresa();
	private static FrmCambiarAlumSede cambiarAlumSede = new FrmCambiarAlumSede();
	private static FrmDelEmpresa delEmpresa = new FrmDelEmpresa();
	private static FrmCrearAlumProf crearAlumProf = new FrmCrearAlumProf();

	@SuppressWarnings("serial")
	public MainFrm() {
		super("FCT");

		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenFondo/Fondo_FCT.jpg"));
		Image image = icon.getImage();

		escritorio = new JDesktopPane() {

			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		getContentPane().setLayout(null);
		this.setContentPane(escritorio);

		addFrames();
		setFrameBounds();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Segoe Print", Font.BOLD, 10));
		menuBar.add(comboBox);
		comboBox.setBounds(50, 7, 700, 22);
		comboBox.addItem("Elegir una opcion:");
		comboBox.addItem("Dar de alta a un profesor");
		comboBox.addItem("Dar de alta a un alumno");
		comboBox.addItem("Dar de alta a una empresa");
		comboBox.addItem("Dar de alta a una sede");
		comboBox.addItem("Asignar empresa a alumno");
		comboBox.addItem("Asignar alumno a profesor");
		comboBox.addItem("Mostrar alumnos (con empresa asignada) por profesor");
		comboBox.addItem("Mostrar todos los alumnos que no tengan empresa asignada");
		comboBox.addItem("Mostrar el nombre del profesor que tiene mas alumnos con empresa asignada");
		comboBox.addItem("Cambiar a alumnos en una empresa de una sede a otra");
		comboBox.addItem("Eliminar empresa");
		comboBox.addItem("Exportar alumnos por porfesor");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 1; i <= 12; i++)
					if (comboBox.getSelectedIndex() == i)
						displayIFrame();

			}

		});
	}

	private void addFrames() {
		frames.add(null);
		frames.add(1, frmAltaProfesor);
		frames.add(2, frmAltaAlumno);
		frames.add(3, frmAltaEmpresa);
		frames.add(4, frmAltaSede);
		frames.add(5, asignEmpresaAlum);
		frames.add(6, asignAlumProf);
		frames.add(7, mostrarAlumnosConEmpresaProfesor);
		frames.add(8, mostrarAlumnosSinEmpresa);
		frames.add(9, profesorConMasAlumnosEmpresa);
		frames.add(10, cambiarAlumSede);
		frames.add(11, delEmpresa);
		frames.add(12, crearAlumProf);

		escritorio.add(frmAltaProfesor);
		escritorio.add(frmAltaAlumno);
		escritorio.add(frmAltaEmpresa);
		escritorio.add(frmAltaSede);
		escritorio.add(asignEmpresaAlum);
		escritorio.add(asignAlumProf);
		escritorio.add(mostrarAlumnosConEmpresaProfesor);
		escritorio.add(mostrarAlumnosSinEmpresa);
		escritorio.add(profesorConMasAlumnosEmpresa);
		escritorio.add(cambiarAlumSede);
		escritorio.add(delEmpresa);
		escritorio.add(crearAlumProf);

	}

	private void setFrameBounds() {
		frmAltaProfesor.setBounds(83, 63, 500, 300);
		frmAltaAlumno.setBounds(83, 63, 500, 300);
		frmAltaEmpresa.setBounds(83, 63, 500, 300);
		frmAltaSede.setBounds(83, 63, 500, 300);
		asignEmpresaAlum.setBounds(83, 63, 500, 300);
		asignAlumProf.setBounds(83, 63, 500, 284);
		mostrarAlumnosConEmpresaProfesor.setBounds(83, 63, 500, 300);
		mostrarAlumnosSinEmpresa.setBounds(86, 63, 230, 130);
		profesorConMasAlumnosEmpresa.setBounds(86, 63, 270, 130);
		cambiarAlumSede.setBounds(86, 63, 500, 300);
		delEmpresa.setBounds(86, 63, 333, 100);
		crearAlumProf.setBounds(86, 63, 333, 100);
	}

	private void displayIFrame() {
		for (int i = 0; i < frames.size(); i++)
			if (comboBox.getSelectedIndex() == frames.indexOf(frames.get(i)))
				if (frames.get(i).isVisible())
					escritorio.moveToFront(frames.get(i));
				else
					frames.get(i).setVisible(true);

	}

	public static void main(String[] args) {

		final MainFrm v = new MainFrm();
		SwingUtilities.invokeLater(() -> {

			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(50, 50);
			v.setSize(1400, 700);

		});
	}

}
