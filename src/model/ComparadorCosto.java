package model;

import java.util.Comparator;

public class ComparadorCosto implements Comparator<Producto> {
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return p2.getCosto().compareTo(p1.getCosto());
	}

}
