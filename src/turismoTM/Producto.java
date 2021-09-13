package turismoTM;

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

	protected Double getTiempo() {
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

}
