package equipoDaoImpl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;

import beans.Equipo;
import beans.Jugador;
import dao.EquipoDao;
import exception.EquipoExisteException;
import io.ObjectOutputStreamSinHeader;

public class EquipoDaoImpl implements EquipoDao {

	
	private File ficheroEquipos;

	public EquipoDaoImpl(File ficheroEquipos) {
		super();
		this.ficheroEquipos = ficheroEquipos;
	}

	public EquipoDaoImpl() {
		super();
	}

	@Override
	public void createEquipo(String nombreEquipo)
			throws FileNotFoundException, IOException, ClassNotFoundException, EquipoExisteException {

		try (ObjectOutputStream oos = (ficheroEquipos.exists())
				? new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroEquipos, true))
				: new ObjectOutputStream(new FileOutputStream(ficheroEquipos))) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {

				boolean finArchivo = false;
				while (!finArchivo)
					try {
						Equipo equipo = (Equipo) ois.readObject();
						if (equipo.getNombre().equals(nombreEquipo))
							throw new EquipoExisteException();
					} catch (EOFException e) {
						finArchivo = true;
					}
				oos.writeObject(new Equipo(nombreEquipo));
			}
		}

	}

	@Override
	public void insJugador(String nombreEquipo, Jugador jugador)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File tempFile = new File("./datos/temp");

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {
			try (ObjectOutputStream oos = (tempFile.exists() && tempFile.length() > 0)
					? new ObjectOutputStreamSinHeader(new FileOutputStream(tempFile, true))
					: new ObjectOutputStream(new FileOutputStream(tempFile))) {

				boolean finArchivo = false;
				while (!finArchivo) {
					try {
						Equipo equipo = (Equipo) ois.readObject();
						if (equipo.getNombre().equals(nombreEquipo)) {
							if (!equipo.getJugadores().contains(jugador)) {
								equipo.getJugadores().add(jugador);
							}
						}
						oos.writeObject(equipo);
					} catch (EOFException e) {
						finArchivo = true;
					}
				}
			}
		}

		Files.move(tempFile.toPath(), ficheroEquipos.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	@Override
	public void deleteJugador(String nombreEquipo, String nombreJugador)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File tempFile = new File("./datos/temp");

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {
			try (ObjectOutputStream oos = (tempFile.exists() && tempFile.length() > 0)
					? new ObjectOutputStreamSinHeader(new FileOutputStream(tempFile, true))
					: new ObjectOutputStream(new FileOutputStream(tempFile))) {

				boolean finArchivo = false;
				while (!finArchivo) {
					try {
						Equipo equipo = (Equipo) ois.readObject();
						if (equipo.getNombre().equals(nombreEquipo)) {
							if (equipo.getJugadores().contains(new Jugador(nombreJugador)))
								equipo.getJugadores().remove(new Jugador(nombreJugador));
						}
						oos.writeObject(equipo);
					} catch (EOFException e) {
						finArchivo = true;
					}
				}
			}
		}
		Files.move(tempFile.toPath(), ficheroEquipos.toPath(), StandardCopyOption.REPLACE_EXISTING);

	}

	@Override
	public void updatePosition(String nombreEquipo, String nombreJugador, int posicion)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File tempFile = new File("./datos/temp");

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {
			try (ObjectOutputStream oos = (tempFile.exists() && tempFile.length() > 0)
					? new ObjectOutputStreamSinHeader(new FileOutputStream(tempFile, true))
					: new ObjectOutputStream(new FileOutputStream(tempFile))) {

				boolean finArchivo = false;
				while (!finArchivo)
					try {
						Equipo equipo = (Equipo) ois.readObject();
						if (equipo.getNombre().equals(nombreEquipo))
							for (Jugador j : equipo.getJugadores())
								if (j.getNombre().equals(nombreJugador))
									j.setPosicion(posicion);

						oos.writeObject(equipo);
					} catch (EOFException e) {
						finArchivo = true;
					}
			}

		}
		Files.move(tempFile.toPath(), ficheroEquipos.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	@Override
	public Equipo listEquipo(String nombreEquipo) throws IOException, ClassNotFoundException {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {

			boolean finArchivo = false;
			while (!finArchivo) {
				try {
					Equipo equipo = (Equipo) ois.readObject();
					if (equipo.getNombre().equals(nombreEquipo)) {
						return equipo;
					}

				} catch (EOFException e) {
					finArchivo = true;
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<String> listEquipos() throws IOException, ClassNotFoundException {
		
		ArrayList<String> equipos = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroEquipos))) {

			boolean finArchivo = false;
			while (!finArchivo) {
				try {
					Equipo equipo = (Equipo) ois.readObject();
					equipos.add("Equipo: " + equipo.getNombre());
				} catch (EOFException e) {
					finArchivo = true;
				}
			}
		}
		return equipos;
	}
}