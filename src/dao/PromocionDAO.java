package dao;

import turismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findPromocionByNombre(String nombre);
}
