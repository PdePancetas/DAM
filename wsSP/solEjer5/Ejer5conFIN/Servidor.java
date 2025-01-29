package Ejer5conFIN;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 12345;
    static Map<Integer, List<ManejadorCliente>> grupos = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en el puerto " + PUERTO);

        while (true) {
            try {
                Socket clienteSocket = servidor.accept();
                System.out.println("Nuevo cliente conectado desde: " + clienteSocket.getInetAddress());

                Thread manejadorThread = new Thread(new ManejadorCliente(clienteSocket));
                manejadorThread.start();
            } catch (IOException e) {
                System.err.println("Error al aceptar la conexión: " + e.getMessage());
            }
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private int numeroN;

    public ManejadorCliente(Socket socket) throws IOException {
        this.socket = socket;
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.salida = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            salida.println("Bienvenido. Introduce un número N:");
            String nStr = entrada.readLine();
            try {
                numeroN = Integer.parseInt(nStr);
            } catch (NumberFormatException e) {
                salida.println("Entrada inválida. Por favor, introduce un número entero.");
                return; // Termina el hilo si la entrada no es un número.
            }

            synchronized (Servidor.grupos) {
                Servidor.grupos.computeIfAbsent(numeroN, k -> new ArrayList<>()).add(this);
            }
            System.out.println("Cliente asignado al grupo " + numeroN);

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido de cliente en grupo " + numeroN + ": " + mensaje);
                if (mensaje.equals(":FIN:")) {
                    System.out.println("Cliente en grupo " + numeroN + " se ha desconectado.");
                    break; // Sale del bucle para cerrar la conexión
                }
                reenviarMensaje(mensaje, numeroN);
            }
        } catch (IOException e) {
            System.err.println("Cliente en grupo " + numeroN + " se ha desconectado.");
        } finally {
            try {
                socket.close();
                synchronized (Servidor.grupos) {
                    if (Servidor.grupos.containsKey(numeroN)) {
                        Servidor.grupos.get(numeroN).remove(this);
                        if (Servidor.grupos.get(numeroN).isEmpty()) {
                            Servidor.grupos.remove(numeroN);
                        }
                    }
                }
                System.out.println("Cliente desconectado.");
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    private void reenviarMensaje(String mensaje, int numeroN) {
        synchronized (Servidor.grupos) {
            if (Servidor.grupos.containsKey(numeroN)) {
                for (ManejadorCliente cliente : Servidor.grupos.get(numeroN)) {
                    if (cliente != this) {
                        cliente.enviarMensaje("Mensaje de otro cliente: " + mensaje);
                    }
                }
            }
        }
    }
}