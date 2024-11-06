package logics;

import java.util.Comparator;

import model.Alumno;

public class CriterioNumMatricula implements Comparator<Alumno> {

	@Override
	public int compare(Alumno o1, Alumno o2) {

		return o1.getNumMatricula() - o2.getNumMatricula();
	}

}
