package dao;

import java.util.Map;

import turismoTM.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion>{
	
	public abstract Atraccion findAtraccionByNombre(String nombre);
	
	public abstract Map<String, Atraccion> findAllAtracciones();
}