package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.ConnectionProvider;
import model.Itinerario;
import model.Producto;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public List<Itinerario> findAll() {
		return null;
	}

	/*
	 * Recibe el nombre de un usuario y el mapa de todos productos. Devuelve un
	 * objeto de tipo itinerario si encuentra productos en la db ya comprados por
	 * ese usuario o null si nunca compró un producto.
	 */
	public Itinerario findItinerarioByUsuario(String idUsuario, Map<String, Producto> productos) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			Itinerario itinerario = null;
			String sql = "SELECT id_usuario, id_producto FROM itinerarios WHERE id_usuario = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, idUsuario);

			ResultSet resultados = statement.executeQuery();

			if (resultados.next())
				itinerario = toItinerario(resultados, productos);

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			int totalItinerarios = 0;
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT count(DISTINCT id_usuario) AS 'total_itinerarios' FROM itinerarios";
			PreparedStatement statement = conn.prepareStatement(sql);

			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				totalItinerarios = resultados.getInt("total_itinerarios");
			}
			return totalItinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(Itinerario it) {
		try {
			int totalInsertado = 0;
			Connection conn = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerarios (id_usuario, id_producto) VALUES (? , ?);";
			PreparedStatement statement = conn.prepareStatement(sql);

			for (Producto prod : it.getProductos()) {
				statement.setString(1, it.getNombreUsuario());
				statement.setString(2, prod.getNombre());
				totalInsertado += statement.executeUpdate();
			}

			return totalInsertado;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int update(Itinerario it) {
		try {
			int totalInsertado = 0;
			Connection conn = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerarios (id_usuario, id_producto) VALUES (? , ?);";
			PreparedStatement statement = conn.prepareStatement(sql);

			for (String nombreProd : it.getNuevosProductos()) {
				statement.setString(1, it.getNombreUsuario());
				statement.setString(2, nombreProd);
				totalInsertado += statement.executeUpdate();
			}
			return totalInsertado;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Itinerario t) {
		return 0;
	}

	private Itinerario toItinerario(ResultSet resultados, Map<String, Producto> productos) throws SQLException {

		Itinerario itinerario = new Itinerario(resultados.getString("id_usuario"));
		do {
			itinerario.addProducto(productos.get(resultados.getString("id_producto")));
		} while (resultados.next());

		itinerario.setPrimeraCompraFalso();

		return itinerario;
	}
}