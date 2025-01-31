package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import mensaje.Mensaje;

public class Cliente {
    private final String SERVIDOR_IP = "localhost";
    private final int PUERTO = 12345;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Scanner scanner;
    private RecibirMensajes recibirMensajes;
    private EnviarMensajes enviarMensajes;
    private int numConexion = 0;

    public Cliente() throws IOException {
        this.socket = new Socket(SERVIDOR_IP, PUERTO);
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.scanner = new Scanner(System.in);
        this.recibirMensajes = new RecibirMensajes();
        this.enviarMensajes = new EnviarMensajes();
    }

    public void iniciar() {
        Thread recibirMensajesThread = new Thread(recibirMensajes);
        Thread enviarMensajesThread = new Thread(enviarMensajes);

        recibirMensajesThread.start();
        enviarMensajesThread.start();

        try {
        	recibirMensajesThread.join();
        	recibirMensajesThread.interrupt();
            enviarMensajesThread.join();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar a que los hilos terminen: " + e.getMessage());
        }
        System.out.println("Cliente desconectado");
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente();
            cliente.iniciar();
        } catch (IOException e) {
            System.err.println("Error al iniciar el cliente: " + e.getMessage());
        }
    }

    private class RecibirMensajes implements Runnable {
        @Override
        public void run() {
        	while (true) {
	        	try {
					Mensaje mensaje = (Mensaje) ois.readObject();
					String respuestaString = (String) mensaje.getDatos();
					
					System.out.println(respuestaString);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
        }
    }

    private class EnviarMensajes implements Runnable {
        @Override
        public void run() {
        	while (true) {
				int mensaje = scanner.nextInt();
	        	Mensaje m = new Mensaje(numConexion++, mensaje);
	        	try {
					oos.writeObject(m);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }
}