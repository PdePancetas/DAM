package ExamenNetwork2425;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class GestorCliente implements Runnable {

	private Socket socket;
	private ArrayList<Tarea> tareasCliente;

	private BufferedReader br;
	private BufferedWriter bw;
	private ObjectOutputStream oos;

	public GestorCliente(Socket socket) {
		super();
		this.socket = socket;
		this.tareasCliente = new ArrayList<>();
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			try {
				br.close();
				bw.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			enviarOpciones(bw);
			boolean acabado = false;
			while (!acabado) {
				String comando = br.readLine();

				if (comando.split(",")[0].equalsIgnoreCase("AÑADIR")) {
					try {
						Tarea nuevaTarea = new Tarea(comando.split(",")[1], comando.split(",")[2]);

						Optional<Tarea> t = tareasCliente.stream()
								.filter(tarea -> tarea.nombre.equals(nuevaTarea.nombre)).findFirst();

						if (t.isPresent()) {
							for (Tarea tarea : tareasCliente) {
								if (tarea.nombre.equals(t.get().nombre)) {
									tarea.descripcion += " UPDATE " + nuevaTarea.descripcion;
									tarea.marcarPendiente();
								}
							}
							bw.write("Tarea actualizada con éxito");
						} else {
							tareasCliente.add(nuevaTarea);
							bw.write("Tarea añadida con éxito");
						}
						bw.newLine();
						bw.flush();
					} catch (Exception e) {
						bw.write("Hubo un error al añadir la tarea a la lista");
						bw.newLine();
						bw.flush();
					}
				} else if (comando.split(",")[0].equalsIgnoreCase("COMPLETAR")) {

					Tarea tareaAActualizar = new Tarea();
					tareaAActualizar.nombre = comando.split(",")[1];
					Optional<Tarea> t = tareasCliente.stream()
							.filter(tarea -> tarea.nombre.equals(tareaAActualizar.nombre)).findFirst();

					if (t.isPresent()) {
						for (Tarea tarea : tareasCliente) {
							if (tarea.nombre.equals(tareaAActualizar.nombre)) {
								tarea.marcarCompletada();
							}
						}
						bw.write("Tarea completada con éxito");

					} else {
						tareaAActualizar.descripcion = "";
						tareaAActualizar.marcarPendiente();
						tareasCliente.add(tareaAActualizar);
						bw.write("No se encontro la tarea especificada");

					}
					bw.newLine();
					bw.flush();

				} else if (comando.equalsIgnoreCase("LISTAR")) {

					// CUALQUIER MODIFICACION POR COMANDOS ANTERIORES MODIFICA LA LISTA
					// PERO UNA VEZ QUE SE ENVIA, EL UNICO COMANDO QUE FUNCIONA
					// ES AÑADIR (nuevas tareas), NI MODIFICAR UNA NI COMPLETARLA SE VEN
					// REFLEJADOS LOS CAMBIOS CUANDO SE ENVIA LA LISTA AL CLIENTE

					oos.reset(); //El comando que lo solucionó todo. Alabado sea
					oos.writeObject(tareasCliente);
					oos.flush();

				} else if (comando.split(",")[0].equalsIgnoreCase("BORRAR")) {
					try {

						tareasCliente.remove(new Tarea(comando.split(",")[1], null));

						bw.write("Se ha borrado con éxito la tarea de la lista");
					} catch (Exception e) {
						bw.write("Hubo un error al borrar la tarea a la lista");
					}

					bw.newLine();
					bw.flush();

				} else if (comando.equalsIgnoreCase("SALIR")) {

					System.out.println("Cliente " + socket.getLocalAddress() + " desconectandose");
					acabado = true;
					break;

				} else {
					bw.write("El comando introducido no existe");
					bw.newLine();
					bw.flush();
				}

			}
			System.out.println("Cliente " + socket.getLocalAddress() + " se ha desconectado");
			br.close();
			bw.close();
			oos.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Cliente " + socket.getLocalAddress() + " se ha desconectado");

		}

	}

	private void enviarOpciones(BufferedWriter bw) throws IOException {
		bw.write("AÑADIR - (uso: AÑADIR,nombre_tarea,descripcion_tarea)");
		bw.newLine();
		bw.write("COMPLETAR TAREA - (uso: COMPLETAR,nombre_tarea)");
		bw.newLine();
		bw.write("LISTAR TAREAS - (uso: LISTAR)");
		bw.newLine();
		bw.write("BORRAR TAREA - (uso: BORRAR,nombre_tarea");
		bw.newLine();
		bw.write("SALIR - (uso: SALIR)");
		bw.newLine();
		bw.write("");
		bw.newLine();
		bw.flush();

	}

}
