package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.ErrorDatosException;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	@Override
	public Map<String, Atraccion> findAllAtracciones() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			Map<String, Atraccion> atracciones = new HashMap<String, Atraccion>();
			while (resultados.next()) {
				atracciones.put(resultados.getString("nombre"),toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Atraccion atr) {
		try {
			String sql = "INSERT INTO ATRACCIONES (NOMBRE, TIEMPO, COSTO, CUPO, TIPO) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atr.getNombre());
			statement.setDouble(2, atr.getTiempo());
			statement.setDouble(3, atr.getCosto());
			statement.setInt(4, atr.getCupo());
			statement.setObject(5, atr.getTipo());
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atr) {
		try {
			String sql = "UPDATE ATRACCIONES SET CUPO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, atr.getCupo());
			statement.setString(2, atr.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atr) {
		try {
			String sql = "DELETE FROM ATRACCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atr.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion findAtraccionByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM ATRACCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atr = null;

			if (resultados.next()) {
				atr = toAtraccion(resultados);
			}

			return atr;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Atraccion toAtraccion(ResultSet resultados) throws SQLException, ErrorDatosException {
		return new Atraccion(resultados.getString("nombre"), 
							 resultados.getDouble("costo"),
							 resultados.getDouble("tiempo"),
							 resultados.getInt("cupo"),
							 Tipo.valueOf(resultados.getString("tipo")));
	}

	@Override
	public List<Atraccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
