package Ejer5conFIN;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private final String SERVIDOR_IP = "localhost";
    private final int PUERTO = 12345;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private Scanner scanner;
    private RecibirMensajes recibirMensajes;
    private EnviarMensajes enviarMensajes;

    public Cliente() throws IOException {
        this.socket = new Socket(SERVIDOR_IP, PUERTO);
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.salida = new PrintWriter(socket.getOutputStream(), true);
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
            enviarMensajesThread.join();
            recibirMensajesThread.interrupt();
            recibirMensajesThread.join();
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
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                System.err.println("Conexión con el servidor finalizada.");
            }
        }
    }

    private class EnviarMensajes implements Runnable {
        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = scanner.nextLine()) != null) {
                    salida.println(mensaje);
                    if (mensaje.equals(":FIN:")) {
                        socket.close();
                        scanner.close();
                        System.out.println("Conexión finalizada por el cliente.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al enviar mensajes o cerrar el socket: " + e.getMessage());
            }
        }
    }
}