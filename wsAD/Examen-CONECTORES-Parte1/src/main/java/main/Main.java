package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexionBD;

public class Main {

	public static void main(String[] args) throws SQLException {

		// AQUÍ PARA PROBAR LOS MÉTODOS
//		addProduct(1, 1, 5);
//		mostrarEmail(1);
//		usuarioMasPedidos();

	}

	/**
	 * Método para añadir a un pedido existente un producto ya existente, en la
	 * cantidad indicada.
	 * 
	 * @param idProd
	 * @param idPed
	 * @param cantidad
	 */
	private static void addProduct(int idProd, int idPed, int cantidad) {
		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO detalles_pedido VALUES(?,?,?)");
			ps.setDouble(1, cantidad);
			ps.setInt(2, idPed);
			ps.setInt(3, idProd);

			ps.execute();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método para mostrar el email del usuario que ha realizado el pedido indicado
	 * 
	 * @param idPed
	 */
	private static void mostrarEmail(int idPed) {
		Connection con = ConexionBD.getConex();

		try {
			PreparedStatement ps = con.prepareStatement("SELECT u.email FROM usuarios u "
					+ "JOIN pedidos p ON p.id_usuario = u.id_usuario WHERE p.id_pedido = ?");
			ps.setInt(1, idPed);

			ResultSet result = ps.executeQuery();

			while (result.next())
				System.out.println("Email del usuario con pedido " + idPed + " : " + result.getString("email"));

			ps.execute();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método para mostrar el usuario que más pedidos ha realizado
	 */
	private static void usuarioMasPedidos() {

		Connection con = ConexionBD.getConex();
		try {
			
			PreparedStatement ps1 = con.prepareStatement("SELECT id_usuario, COUNT(id_usuario) FROM pedidos GROUP BY id_usuario ORDER BY COUNT(id_usuario) DESC LIMIT 1");
			
			ResultSet result1 = ps1.executeQuery();
			
			int idUsuario = 0;
			
			while(result1.next())
				idUsuario = result1.getInt("id_usuario");
			
			
			
			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM usuarios "
					+ "WHERE id_usuario = ?");
			ps2.setInt(1, idUsuario);
			
			
			ResultSet result = ps2.executeQuery();

			while (result.next())
				System.out.println("El usuario con más pedidos es "+result.getString("nombre") +" con id "+result.getInt("id_usuario"));

			
			
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
