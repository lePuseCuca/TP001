package turismoTM;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private double descuento;
	
	public PromocionPorcentual(String nombre, Tipo tipo, List<Atraccion> atracciones, double descuento) {
		super(nombre, tipo, atracciones);
		this.descuento = descuento;
		setCosto();
	}
	
	public double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;		
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	@Override
	public void setCosto() {
		this.costo = Math.floor(super.getCosto() * ( 1 - (this.descuento/100)));
	}
}
