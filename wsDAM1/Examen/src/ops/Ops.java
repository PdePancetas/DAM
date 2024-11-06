package ops;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import data.Estudiante;
import data.Grupo;
import view.Sistema;

public class Ops {

	public static ArrayList<Estudiante> cargarEstudiantesTxt(File f) throws IOException {

		ArrayList<Estudiante> students = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea;
		while ((linea = br.readLine()) != null) {
			Estudiante est = new Estudiante();

			String[] data = linea.split(",");
			est.setEdad(Integer.parseInt(data[0]));
			est.setNombre(data[1]);

			if (Sistema.getGrupos().contains(new Grupo(data[2]))) {
				for (Grupo g : Sistema.getGrupos())
					if (g.getIdentificador().equals(data[2]))
						est.setGrupo(g);
			} else {
				Grupo g = new Grupo();
				g.setIdentificador(data[2]);
				est.setGrupo(g);
				Sistema.getGrupos().add(g);
			}

			students.add(est);

		}

		br.close();
		return students;
	}

	@SuppressWarnings("unchecked")
	public static HashSet<Estudiante> cargarEstudiantesDat(File f) throws IOException, ClassNotFoundException {
		HashSet<Estudiante> students = null;

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean fin = false;
		while (!fin) {
			try {
				students = (HashSet<Estudiante>) ois.readObject();
			} catch (EOFException e) {
				fin = true;
			}
		}
		ois.close();
		return students;
	}

	public static void guardarEstudiantes(String estud) throws IOException {

		HashSet<Estudiante> students = new HashSet<>();

		String[] est = estud.split(";");

		for (String s : est) {
			Estudiante es = new Estudiante();

			String[] data = s.split(",");
			es.setEdad(Integer.parseInt(data[0]));
			es.setNombre(data[1]);

			if (Sistema.getGrupos().contains(new Grupo(data[2]))) {
				for (Grupo g : Sistema.getGrupos())
					if (g.getIdentificador().equals(data[2]))
						es.setGrupo(g);
			} else {
				Grupo g = new Grupo();
				g.setIdentificador(data[2]);
				es.setGrupo(g);
				Sistema.getGrupos().add(g);
			}

			students.add(es);
		}

		File f = new File(".\\Estudiantes.dat");

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

		oos.writeObject(students);
		oos.close();

	}

}
