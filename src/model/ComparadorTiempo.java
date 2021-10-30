package model;

import java.util.Comparator;

public class ComparadorTiempo implements Comparator<Producto> {
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return p2.getTiempo().compareTo(p1.getTiempo());
	}

}
