package data;

public class Datos {

	private static String[] modulos = { "Acceso a Datos", "Programación de servicios y procesos",
			"Desarrollo de Interfaces", "Programación Multimedia y Dispositivos Móviles",
			"Sistemas de Gestión Empresarial", "Empresa e iniciativa emprendedora" };

	private static boolean[] permiteFCT = { false, true, false, false, true, true };

	private static int[] horas = { 6, 3, 6, 5, 5, 3 };

	private static double[] notas = { 8.45, 9.0, 8.0, 7.34, 8.2, 7.4 };

	public static String[] getModulos() {
		return modulos;
	}

	public static void setModulos(String[] modulos) {
		Datos.modulos = modulos;
	}

	public static boolean[] getPermiteFCT() {
		return permiteFCT;
	}

	public static void setPermiteFCT(boolean[] permiteFCT) {
		Datos.permiteFCT = permiteFCT;
	}

	public static int[] getHoras() {
		return horas;
	}

	public static void setHoras(int[] horas) {
		Datos.horas = horas;
	}

	public static double[] getNotas() {
		return notas;
	}

	public static void setNotas(double[] notas) {
		Datos.notas = notas;
	}

}
