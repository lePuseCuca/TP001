package model;

import java.util.List;
import java.util.Objects;

public abstract class Producto {
	protected String nombre;
	protected double costo;
	protected double tiempo;
	protected Tipo tipo;
	
	public Producto() {}
	
	public Producto(String nombre, Tipo tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Double getTiempo() {
		return this.tiempo;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Double getCosto() {
		return this.costo;
	}	
	
	public boolean esPromocion() {
		return false;
	}
	
	protected abstract boolean hayCupo();
	
	protected abstract List<Atraccion> getAtracciones();
	
	protected abstract boolean venderProducto();
	
	protected boolean validarDatoNumerico(double dato) {
		return (dato > 0);
	}
	
	protected boolean validarDatoNumerico(int dato) {
		return (dato > 0);
	}
	
		
	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}	

}
