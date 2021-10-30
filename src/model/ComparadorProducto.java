package model;

import java.util.Comparator;
import java.util.List;

public class ComparadorProducto implements Comparator<Producto> {
	
	private List<Comparator <Producto>> comparadores;
	
	public ComparadorProducto (List<Comparator <Producto>> comparadores) {
		this.comparadores = comparadores;
	}
	
	@Override
	public int compare(Producto p1, Producto p2) {
		int resultado = 0;
		for (Comparator<Producto> comp : this.comparadores) {
			resultado = comp.compare(p1, p2);
			if (resultado != 0) break;
		}
		
		return resultado;
	}

}
