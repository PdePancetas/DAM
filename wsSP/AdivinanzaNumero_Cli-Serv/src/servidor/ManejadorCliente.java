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
	private Integer numAleatorio;
	private int numGrupo;
	private Random r = new Random();

	public ManejadorCliente(Socket socket, int numGrupo, int numAleatorio) throws IOException {
		this.socket = socket;
		this.entrada = new ObjectInputStream(socket.getInputStream());
		this.salida = new ObjectOutputStream(socket.getOutputStream());
		this.numGrupo = numGrupo;
		this.numAleatorio = numAleatorio;
	}

	@Override
	public void run() {
		try {

			
			int numeroCliente;
			boolean deNuevo = true;
			while (deNuevo) {
				Mensaje m = new Mensaje(0, "Intenta adivinar el numero: ");

				enviarMensaje(m, "Intenta adivinar el numero: ");
				
				m = (Mensaje) entrada.readObject();

				numeroCliente = (Integer) m.getDatos();
				m.setNumConexion(m.getNumConexion() + 1);

				

				if (numeroCliente < numAleatorio) {
					enviarMensaje(m, "El numero es mayor, turno del otro jugador");

					synchronized (Servidor.grupos.get(numGrupo)) {
						Servidor.grupos.get(numGrupo).notify();
						Servidor.grupos.get(numGrupo).wait();
					}

				} else if (numeroCliente > numAleatorio) {
					enviarMensaje(m, "El numero es menor, turno del otro jugador");

					synchronized (Servidor.grupos.get(numGrupo)) {
						Servidor.grupos.get(numGrupo).notify();
						Servidor.grupos.get(numGrupo).wait();
					}

				} else {
					enviarMensaje(m, "Has adivinado el numero");

					synchronized (Servidor.grupos) {
						if (Servidor.grupos.containsKey(numGrupo)) {
							for (ManejadorCliente cliente : Servidor.grupos.get(numGrupo)) {
								if (cliente != this && cliente.equals(Servidor.grupos.get(numGrupo).get(1))) {
									
									cliente.enviarMensaje(m, "El jugador 1 adivinó el número, tienes una ultima oportunidad para adivinarlo:");
									m = (Mensaje) cliente.entrada.readObject();

									int ultimoN = (Integer) m.getDatos();
									m.setNumConexion(m.getNumConexion() + 1);

									if (ultimoN == numAleatorio) {
										Servidor.grupos.get(numGrupo).get(0).enviarMensaje(m, "Habeis quedado empate!");
										Servidor.grupos.get(numGrupo).get(1).enviarMensaje(m, "Habeis quedado empate!");

									} else if (ultimoN != numAleatorio) {
										Servidor.grupos.get(numGrupo).get(0).enviarMensaje(m, "Has ganado!");
										Servidor.grupos.get(numGrupo).get(1).enviarMensaje(m, "Ha ganado el jugador 1!");
									}
									
								} else if (cliente != this && cliente.equals(Servidor.grupos.get(numGrupo).get(0))) {
									Servidor.grupos.get(numGrupo).get(0).enviarMensaje(m, "Ha ganado el jugador 2!");
									Servidor.grupos.get(numGrupo).get(1).enviarMensaje(m, "Has ganado!");
								

								}
							}
						}

					}

					m.setDatos("Quieres empezar de nuevo (s-1/n-0)");
					enviarMensaje(m, "Quieres empezar de nuevo (s-1/n-0)");
					m = (Mensaje) entrada.readObject();
					int res = (Integer) m.getDatos();
					m.setNumConexion(m.getNumConexion() + 1);

					synchronized (Servidor.grupos.get(numGrupo)) {
						// comparar la respuesta del cliente1 con la respuesta del cliente 2
						if (res == 1) {// si la respuesta
							deNuevo = true;

							// llamar otra vez al hilo
							numAleatorio = r.nextInt(1, 1001);
							System.out.println("Nueva partida, nuevo numero: "+numAleatorio);

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
			Mensaje m = new Mensaje();
			m.setNumConexion(mensaje.getNumConexion());
			
			m.setDatos(cadena);

			salida.writeObject(m);
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