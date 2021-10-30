package model;

import java.util.Comparator;

public class ComparadorClase implements Comparator<Producto> {
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return Boolean.compare(p2.esPromocion(), p1.esPromocion());
	}

}
