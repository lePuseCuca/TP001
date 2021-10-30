package model;

import java.util.Comparator;

public class ComparadorTipoPreferido implements Comparator<Producto> {
	
	private Tipo tipoPreferido;
	
	public ComparadorTipoPreferido (Tipo preferido) {
		this.tipoPreferido = preferido;
	}
	
	@Override
	public int compare(Producto p1, Producto p2) {
		return Boolean.compare(p2.getTipo() == this.tipoPreferido, p1.getTipo() == this.tipoPreferido);
	}

}
