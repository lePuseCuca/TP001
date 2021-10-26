package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import turismoTM.Itinerario;
import turismoTM.Producto;

public class ItinerarioDAOImpl implements ItinerarioDAO{

	private Connection conn;	
	
	public ItinerarioDAOImpl() {
		try {
			this.conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}		
	}
	
	public List<Itinerario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public Itinerario findItinerarioByUsuario(String idUsuario, Map<String, Producto> productos) {
		try {
			conn = ConnectionProvider.getConnection();
			Itinerario itinerario = new Itinerario(idUsuario);
			String sql = "SELECT id_usuario, id_producto FROM itinerarios WHERE id_usuario = ?;";
			PreparedStatement statement = this.conn.prepareStatement(sql);
			statement.setString(1, idUsuario);
			
			ResultSet resultados = statement.executeQuery();
			
			// Validar si existe producto
		
			
		
		
			
			while(resultados.next()) {
				itinerario.addProducto(productos.get(resultados.getString("id_producto")));
			}
			
			itinerario.setNuevoItinerario(itinerario.getTotalProductos() == 0);
			
			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			int totalItinerarios = 0;
			conn = ConnectionProvider.getConnection();
			String sql = "SELECT count(DISTINCT id_usuario) AS 'total_itinerarios' FROM itinerarios";
			PreparedStatement statement = this.conn.prepareStatement(sql);
					
			ResultSet resultados = statement.executeQuery();
			
			while(resultados.next()) {	
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
			conn = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerarios (id_usuario, id_producto) VALUES (? , ?);";
			PreparedStatement statement = this.conn.prepareStatement(sql);
			
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
			conn = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerarios (id_usuario, id_producto) VALUES (? , ?);";
			PreparedStatement statement = this.conn.prepareStatement(sql);
			
			for (String nombreProd : it.getNuevosProductos()) {
				System.out.println(nombreProd);
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	@SuppressWarnings("unused")
	private Itinerario toItinerario (ResultSet resultados) {
		
		return null;		
	}



}
