package dao;

import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
		
	public abstract List<Promocion> findAll(Map<String, Atraccion> atracciones);
}