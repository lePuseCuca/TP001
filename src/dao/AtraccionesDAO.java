package dao;

import turismoTM.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion>{
	
	public abstract Atraccion findAtraccionByNombre(String nombre);
	
}