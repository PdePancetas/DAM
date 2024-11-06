
public class Repositorio {

	static Alumno[] alumnos = new Alumno[0];

	/*****************************************
	 * //CRUD create, read, update, delete.
	 *****************************************/

	public static void addAlumno(Alumno a) {
		Alumno[] aux = new Alumno[alumnos.length + 1];
		for (int i = 0; i < alumnos.length; i++)
			aux[i] = alumnos[i];
		aux[aux.length - 1] = a;
		alumnos = aux;
	}

	public static void delAlumno(String nif) {

	}

	public static Alumno getAlumno(String nif) {

		for (int i = 0; i < alumnos.length; i++)
			if (alumnos[i].getNif().equals(nif))
				return alumnos[i];
		return null;
	}

	public static void modAlumno(String nif, String nombre, int nota) {

		for (int i = 0; i < alumnos.length; i++)
			if (alumnos[i].getNif().equals(nif)) {
				alumnos[i].setNombre(nombre);
				alumnos[i].setNota(nota);
			}

	}
	
	

}
