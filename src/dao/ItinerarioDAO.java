package dao;

import java.util.List;

import turismoTM.Producto;

public interface ItinerarioDAO extends GenericDAO<Producto> {
	
	public abstract List<Producto> findItinerarioByUserID (String UserID);
	
}
