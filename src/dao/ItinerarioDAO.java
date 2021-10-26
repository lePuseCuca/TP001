package dao;

import java.util.Map;

import turismoTM.Itinerario;
import turismoTM.Producto;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {
	
	public abstract Itinerario findItinerarioByUsuario (String idUsuario, Map<String, Producto> productos);	
	
}
