package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import mensaje.Mensaje;

class ManejadorCliente implements Runnable {
	private Socket socket;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Integer numeroAleatorio;
	private int numGrupo;
	private Random r = new Random();

	public ManejadorCliente(Socket socket, int numGrupo) throws IOException {
		this.socket = socket;
		this.entrada = new ObjectInputStream(socket.getInputStream());
		this.salida = new ObjectOutputStream(socket.getOutputStream());
		this.numGrupo = numGrupo;
	}

	@Override
	public void run() {
		try {

			synchronized (Servidor.grupos) {
				Servidor.grupos.computeIfAbsent(numGrupo, _ -> new ArrayList<>()).add(this);

			}

			synchronized (Servidor.grupos.get(numGrupo)) {
				while (!(Servidor.grupos.get(numGrupo).size() == 2)) {
					System.out.println("Cliente esperando rival...");
					Servidor.grupos.get(numGrupo).wait();
				}
				Servidor.grupos.get(numGrupo).notify();

				numeroAleatorio = r.nextInt(1, 1001);
				if(Servidor.grupos.get(numGrupo).get(0).numeroAleatorio == null) {
				Servidor.grupos.get(numGrupo).get(0).numeroAleatorio = numeroAleatorio;
				Servidor.grupos.get(numGrupo).get(1).numeroAleatorio = numeroAleatorio;
				}
			}

			System.out.println("Clientes asignados al grupo " + numGrupo+"numero a adivinar: "+numeroAleatorio);

			int numeroCliente;
			boolean deNuevo = true;
			while (deNuevo) {
				Mensaje mensaje = new Mensaje(0, "Intenta adivinar el numero: ");

				enviarMensaje(mensaje, "Intenta adivinar el numero: ");
				
				mensaje = (Mensaje) entrada.readObject();

				numeroCliente = (Integer) mensaje.getDatos();
				mensaje.setNumConexion(mensaje.getNumConexion() + 1);

				System.out.println("Mensaje recibido de cliente en grupo " + numGrupo + ": " + numeroCliente);

				if (numeroCliente < numeroAleatorio) {
					enviarMensaje(mensaje, "El numero es mayor, intentalo de nuevo");

					synchronized (Servidor.grupos.get(numGrupo)) {
						Servidor.grupos.get(numGrupo).notify();
						Servidor.grupos.get(numGrupo).wait();
					}

				} else if (numeroCliente > numeroAleatorio) {
					enviarMensaje(mensaje, "El numero es menor, intentalo de nuevo");

					synchronized (Servidor.grupos.get(numGrupo)) {
						Servidor.grupos.get(numGrupo).notify();
						Servidor.grupos.get(numGrupo).wait();
					}

				} else {
					enviarMensaje(mensaje, "Has adivinado el numero");

					synchronized (Servidor.grupos) {
						if (Servidor.grupos.containsKey(numGrupo)) {
							for (ManejadorCliente cliente : Servidor.grupos.get(numGrupo)) {
								if (cliente != this && cliente.equals(Servidor.grupos.get(numGrupo).get(1))) {
									cliente.enviarMensaje(mensaje, "Tiesnes una ultima oportunidad para advinar el numero:");
									mensaje = (Mensaje) entrada.readObject();

									int ultimoN = (Integer) mensaje.getDatos();
									mensaje.setNumConexion(mensaje.getNumConexion() + 1);

									if (ultimoN == numeroAleatorio) {
										Servidor.grupos.get(numGrupo).get(0).enviarMensaje(mensaje,
												"Habeis quedado empate");
										Servidor.grupos.get(numGrupo).get(1).enviarMensaje(mensaje,
												"Habeis quedado empate");

									} else if (ultimoN != numeroAleatorio) {
										Servidor.grupos.get(numGrupo).get(0).enviarMensaje(mensaje, "Has ganado");
										Servidor.grupos.get(numGrupo).get(1).enviarMensaje(mensaje, "Has perdido");

									}
								} else if (cliente != this && cliente.equals(Servidor.grupos.get(numGrupo).get(0))) {
									Servidor.grupos.get(numGrupo).get(0).enviarMensaje(mensaje, "Has perdido");
									Servidor.grupos.get(numGrupo).get(1).enviarMensaje(mensaje, "Has ganado");

								}
							}
						}

					}

					enviarMensaje(mensaje, "Quieres empezar de nuevo (s-1/n-0)");
					mensaje = (Mensaje) entrada.readObject();
					int res = (Integer) mensaje.getDatos();
					mensaje.setNumConexion(mensaje.getNumConexion() + 1);

					synchronized (Servidor.grupos) {
						// comparar la respuesta del cliente1 con la respuesta del cliente 2
						if (res == 1) {// si la respuesta
							deNuevo = true;

							// llamar otra vez al hilo
							numeroAleatorio = r.nextInt(1, 1001);

							// si la
						} else {
							deNuevo = false;
						}
					}
				}

			}
		} catch (IOException e) {
			System.err.println("Cliente en grupo " + numGrupo + " se ha desconectado.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
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

				entrada.close();
				salida.close();
				System.out.println("Cliente desconectado.");
			} catch (IOException e) {
				System.err.println("Error al cerrar el socket: " + e.getMessage());
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(socket);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManejadorCliente other = (ManejadorCliente) obj;
		return Objects.equals(socket, other.socket);
	}

	public void enviarMensaje(Mensaje mensaje, String cadena) {

		try {
			mensaje.setDatos(cadena);

			salida.writeObject(mensaje);
			salida.flush();
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