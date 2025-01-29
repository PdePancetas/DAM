package Ejer5a;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVIDOR_IP, PUERTO);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        Thread recibirMensajesThread = new Thread(new RecibirMensajes(entrada));
        Thread enviarMensajesThread = new Thread(new EnviarMensajes(salida, scanner));

        recibirMensajesThread.start();
        enviarMensajesThread.start();
    }

    
}

class RecibirMensajes implements Runnable {
    private BufferedReader entrada;

    public RecibirMensajes(BufferedReader entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println(mensaje);
            }
        } catch (IOException e) {
            System.err.println("Error al recibir mensajes: " + e.getMessage());
        }
    }
}

 class EnviarMensajes implements Runnable {
    private PrintWriter salida;
    private Scanner scanner;

    public EnviarMensajes(PrintWriter salida, Scanner scanner) {
        this.salida = salida;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = scanner.nextLine()) != null) {
                salida.println(mensaje);
            }
        } catch (NullPointerException e) {
            System.err.println("Error al enviar mensajes: " + e.getMessage());
        }
    }
}