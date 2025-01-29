package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

class ManejadorCliente implements Runnable {
	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private int numeroAleatorio;
	private int numGrupo;
	private int numCliente;

	public ManejadorCliente(Socket socket, int numGrupo, int numAleatorio, int numCliente) throws IOException {
		this.socket = socket;
		this.entrada = new ObjectInputStream(socket.getInputStream());
		this.salida = new ObjectOutputStream(socket.getOutputStream());
		this.numGrupo = numGrupo;
		this.numeroAleatorio = numAleatorio;
		this.numCliente = numCliente;
	}

	@Override
	public void run() {
		try {

			synchronized (Servidor.grupos) {
				Servidor.grupos.computeIfAbsent(numGrupo, k -> new ArrayList<>()).add(this);
			}
			System.out.println("Cliente asignado al grupo " + numGrupo);

			int numeroCliente;
			boolean deNuevo = true;
			while (deNuevo) {

				Mensaje mensaje = (Mensaje) entrada.readObject();

				numeroCliente = (Integer) mensaje.getDatos();
				mensaje.setNumConexion(mensaje.getNumConexion() + 1);

				System.out.println("Mensaje recibido de cliente en grupo " + numGrupo + ": " + numeroCliente);

				if (numeroCliente < numeroAleatorio) {
					enviarMensaje(mensaje, "El numero es mayor");
				} else if (numeroCliente > numeroAleatorio) {
					enviarMensaje(mensaje, "El numero es menor");
				} else {
					enviarMensaje(mensaje, "Has adivinado el numero");

					// REVISAR ESTO//////////////
					synchronized (Servidor.grupos) {
						if (Servidor.grupos.containsKey(numGrupo)) {
							for (ManejadorCliente cliente : Servidor.grupos.get(numGrupo)) {
								if (cliente != this && cliente.numCliente==2) {
									cliente.enviarMensaje(mensaje,
											"Tines una ultima oportunidad para advinar el numero:");
									int ultimoN = entrada.readInt();

									if (ultimoN == numeroAleatorio) {
										Servidor.grupos.get(numGrupo).get(0).enviarMensaje(mensaje, "Habeis quedado empate");
										Servidor.grupos.get(numGrupo).get(1).enviarMensaje(mensaje, "Habeis quedado empate");
										
									} else if (ultimoN != numeroAleatorio) {
										cliente.enviarMensaje(mensaje, "Has perdido");
										enviarMensaje(mensaje, "Has ganado");
									}
								}
							}
						}

					}
					/////////////////////

					enviarMensaje(mensaje, "Quieres empezar de nuevo");
					String res = entrada.readUTF();

					if (res.equals("si")) {
						deNuevo = true;
						Random r = new Random();
						numeroAleatorio = r.nextInt(1, 1001);
					} else {
						deNuevo = false;
					}

				}

			}
		} catch (IOException e) {
			System.err.println("Cliente en grupo " + numGrupo + " se ha desconectado.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				synchronized (Servidor.grupos) {
					if (Servidor.grupos.containsKey(numGrupo)) {
						Servidor.grupos.get(numGrupo).remove(this);
						if (Servidor.grupos.get(numGrupo).isEmpty()) {
							Servidor.grupos.remove(numGrupo);
						}
					}
				}
				System.out.println("Cliente desconectado.");
			} catch (IOException e) {
				System.err.println("Error al cerrar el socket: " + e.getMessage());
			}
		}
	}

	public void enviarMensaje(Mensaje mensaje, String cadena) {

		try {
			mensaje.setDatos(cadena);

			salida.writeObject(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//    private void reenviarMensaje(String mensaje, int numeroN) {
//        synchronized (Servidor.grupos) {
//            if (Servidor.grupos.containsKey(numeroN)) {
//                for (ManejadorCliente cliente : Servidor.grupos.get(numeroN)) {
//                    if (cliente != this) {
//                        cliente.enviarMensaje(mensaje, "Mensaje de otro cliente: " + mensaje);
//                    }
//                }
//            }
//        }
//    }
}