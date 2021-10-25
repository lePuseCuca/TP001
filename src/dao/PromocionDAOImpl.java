package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import turismoTM.Atraccion;
import turismoTM.ErrorDatosException;
import turismoTM.Promocion;
import turismoTM.PromocionAbsoluta;
import turismoTM.PromocionAxB;
import turismoTM.PromocionPorcentual;
import turismoTM.Tipo;
import turismoTM.TipoPromocion;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public int countAll() {
		return 0;
	}

	@Override
	public int insert(Promocion promo) {
		return 0;
	}

	@Override
	public int update(Promocion promo) {
		return 0;
	}

	@Override
	public int delete(Promocion promo) {
		return 0;
	}
	
	@Override
	public List<Promocion> findAll() {
		return null;
	}

	@Override
	public List<Promocion> findAll(Map<String, Atraccion> atracciones) {
		try {
			String sql = "SELECT * FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados, atracciones));
			}
			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet resultados, Map<String, Atraccion> atracciones) throws SQLException {
		Promocion promoNueva = null;
		
		List<String> atraccionesPromo = findAtracionesPromo(resultados.getString(1));
		List<Atraccion> tempAt = new ArrayList<Atraccion>();
		
		boolean atraccionInexistente = false;
		
		for(String nombreAtr : atraccionesPromo)
			if(atracciones.containsKey(nombreAtr))
				tempAt.add(atracciones.get(nombreAtr));
			else
				atraccionInexistente = true; //Ojo que si alguna atracción no existe no se carga nada
		
		switch (resultados.getString(2)) {
		case "PORCENTUAL":
			if(!atraccionInexistente) {
				try {
					promoNueva = new PromocionPorcentual(
							resultados.getString(1), 
							TipoPromocion.valueOf(resultados.getString(2)), 
							tempAt,
							Tipo.valueOf(resultados.getString(3)),
							Double.parseDouble(resultados.getString(4)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ErrorDatosException e) {
					System.err.println("Error en datos de promociones: " + e.getMessage() + ".\nAlgunos productos no se cargaron correctamente.");
				}
			}
			break;			
		case "ABSOLUTA":
			if(!atraccionInexistente) {
				try {
					promoNueva = new PromocionAbsoluta(
								resultados.getString(1), 
								TipoPromocion.valueOf(resultados.getString(2)), 
								tempAt,
								Tipo.valueOf(resultados.getString(3)),
								Double.parseDouble(resultados.getString(4)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ErrorDatosException e) {
					System.err.println("Error en datos de promociones: " + e.getMessage() + ".\nAlgunos productos no se cargaron correctamente.");
				}
			}
			break;
		case "AxB":			
			if(!atraccionInexistente && atracciones.containsKey(resultados.getString(4))) {
				promoNueva = new PromocionAxB(
						resultados.getString(1), 
						TipoPromocion.valueOf(resultados.getString(2)), 
						tempAt,
						atracciones.get(resultados.getString(4)),
						Tipo.valueOf(resultados.getString(3)));
			}
			break;
		}	
		
		return promoNueva;
	}

	private List<String> findAtracionesPromo(String nombre) {
		try {
			String sql = "SELECT * \n"
					+ "FROM promocion_atracciones\n"
					+ "WHERE id_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);;
			ResultSet resultados = statement.executeQuery();

			List<String> atraccionesPromo = new LinkedList<String>();
			
			while (resultados.next()) {
				atraccionesPromo.add(resultados.getString(2));
			}
			return atraccionesPromo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	

}