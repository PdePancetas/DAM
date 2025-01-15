package Ejer4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteManager implements Runnable{
	Socket socket;
	Mensaje m;
	int recPuerto;
	public ClienteManager(Socket s, Mensaje m, int recPuerto)
	{
		this.socket=s;
		this.m=m;
		this.recPuerto=recPuerto;
	}
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			IOUtility.escribir(bw, ""+recPuerto);
			while(true)
			{
				System.out.println("Cliente Manager: leo...");
				String nuevoPuerto=IOUtility.leer(br);
				System.out.println("Cliente Manager: leido "+nuevoPuerto);
				m.setPuerto(Integer.parseInt(nuevoPuerto));
				System.out.println("Cliente Manager: setPuerto Hecho");
				if(Integer.parseInt(nuevoPuerto)<0)
				{
					System.out.println("inicio el anillo");
					m.setPuerto(Integer.parseInt(nuevoPuerto)*(-1));
					InetSocketAddress isa = new InetSocketAddress("localhost", m.getPuerto());
					try {
						
						Socket sc = new Socket();
						sc.connect(isa);
						m.s=sc;
						m.primero=true;
						
						BufferedWriter bw2= new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
						IOUtility.escribir(bw2, "1");
					} catch (Exception e) {
						System.out.println("ERROR");
					}
					System.out.println("Anillo iniciado");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
