package repository;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import data.Estudiante;
import data.Grupo;
import exception.VoidException;
import system.Ventana;

public class Ops {

	public static int loadEstudentsTxt(String nomFichero) throws VoidException, IOException {
		int cont = 0;
		File f = new File(".\\Estudiantes\\" + nomFichero + ".txt");

		if (!f.exists())
			throw new VoidException();

		BufferedReader br = new BufferedReader(new FileReader(f));

		String line;
		while ((line = br.readLine()) != null) {
			Estudiante student = new Estudiante();
			Object[] datos = line.split(",");

			student.setNombre((String) datos[1]);
			student.setEdad(Integer.parseInt(line.split(",")[0]));

			if (Ventana.getGrupos().contains(new Grupo((String) datos[2]))) {
				for (Grupo g : Ventana.getGrupos())
					if (g.getIdentificador().equals((String) datos[2]))
						student.setGrupo(g);
			} else {
				Grupo group = new Grupo((String) datos[2]);
				student.setGrupo(group);
				Ventana.getGrupos().add(group);
			}

			Ventana.getEstudiantes().add(student);
			cont++;

		}
		br.close();
		return cont;
	}

	public static int loadEstudentsDat(String nomFichero) throws VoidException, IOException, ClassNotFoundException {
		int cont = 0;
		File f = new File(".\\Estudiantes\\" + nomFichero + ".dat");

		if (!f.exists())
			throw new VoidException();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

		boolean fin = false;
		while (!fin) {
			try {
				Estudiante student = (Estudiante) ois.readObject();

				if (Ventana.getGrupos().contains(student.getGrupo())) {

					for (Grupo g : Ventana.getGrupos())
						if (g.getIdentificador().equals(student.getGrupo().getIdentificador()))
							student.setGrupo(g);
					;
				} else {
					Ventana.getGrupos().add(student.getGrupo());
				}

				Ventana.getEstudiantes().add(student);
				cont++;
			} catch (EOFException e) {
				fin = true;
			}
		}

		ois.close();
		return cont;
	}

	public static void saveEstudents(HashSet<Estudiante> estudiantes) throws IOException {
		File f = new File(".\\Estudiantes\\Estudiantes.dat");

		ObjectOutputStream oos;
		if (f.exists())
			f.delete();

		f.createNewFile();
		oos = new ObjectOutputStream(new FileOutputStream(f));

		for (Estudiante e : estudiantes)
			oos.writeObject(e);

		oos.close();
	}

}
