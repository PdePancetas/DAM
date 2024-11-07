package logic;

import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.Pedido;
import beans.Producto;
import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {
		JSONArray pedidoJson = null;
		try {
			pedidoJson = Func.leerFicheroJSON("pedidos.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Pedido pedido = new Pedido();
		Producto p;
		for (int i = 0; i < pedidoJson.length(); i++) {
			p = new Producto();
			JSONObject productoJson = pedidoJson.getJSONObject(i);
			p.setNombre(productoJson.getString("nombre"));
			p.setCantidad(productoJson.getInt("cantidad"));
			p.setPrecio(productoJson.getDouble("precio"));
			
			pedido.getProductos().add(p);
		}
		try {
			Func.genInforme(pedido);
			System.out.println("Informe generado en ./reports/");
		} catch (JRException e) {
			System.out.println("Ha habido algun problema para generar el informe");
			e.printStackTrace();
		}

	}

}
