package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

import tiposComunicación.MiMensaje;
import utilidadesTeclado.Teclado;

public class Cliente {

	public static void main(String[] args) {

		try (Socket s = new Socket()) {

			// Obtener ip local
//			String ipLocal = s.getLocalAddress() + " ";

			// Conectar a ip
			s.connect(new InetSocketAddress("192.168.1.59", 7777));

			// BufferedStream
			System.out.println("Comunicación 1:\n");
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			bw.write("Miguel");
			bw.newLine();
			bw.flush();

			String mensaje_recibido = br.readLine();
			System.out.println(mensaje_recibido);

			// ObjectStream
			System.out.println("Comunicación 2:\n");
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			MiMensaje mensaje = new MiMensaje("Miguel", 73, false);

			oos.writeObject(mensaje);

			MiMensaje mensajeRecibido = (MiMensaje) ois.readObject();
			System.out.println("Mensaje recibido: " + mensajeRecibido.toString());

			// DataStream
			System.out.println("Comunicación 3:\n");
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());

			Random r = new Random();
			int cNum = r.nextInt(1, 11);

			dos.writeInt(cNum);
			dos.flush();
			int sNum = dis.readInt();

			System.out.println("cNum: " + cNum + ", sNum: " + sNum);

			// Stream
			System.out.println("Comunicación 4:\n");
			OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
			InputStreamReader is = new InputStreamReader(s.getInputStream());

			char[] chars = new char[sNum];
			for (int i = 0; i < chars.length; i++)
				chars[i] = (char) r.nextInt();

			os.write(chars);
			os.flush();
			int[] bytes = new int[sNum];
			for (int i = 0; i < sNum; i++)
				bytes[i] = chars[i];

			int[] bytesRecibidos = new int[cNum];
			for (int i = 0; i < cNum; i++)
				bytesRecibidos[i] = is.read();

			System.out.println("Bytes creados por cliente: " + Arrays.toString(bytes));
			System.out.println("Bytes recibidos: " + Arrays.toString(bytesRecibidos));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
