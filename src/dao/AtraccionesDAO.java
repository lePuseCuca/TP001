package dao;

import java.util.Map;

import model.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion>{
	
	public abstract Atraccion findAtraccionByNombre(String nombre);
	
	public abstract Map<String, Atraccion> findAllAtracciones();
}