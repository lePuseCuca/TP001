package dao;

import java.util.Map;

import model.Itinerario;
import model.Producto;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {
	
	public abstract Itinerario findItinerarioByUsuario (String idUsuario, Map<String, Producto> productos);	
	
}
