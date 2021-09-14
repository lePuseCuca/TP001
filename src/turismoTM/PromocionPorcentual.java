package turismoTM;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private double descuento;
	
	public PromocionPorcentual(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Tipo tipo, double descuento) {
		super(nombre, tipoPromo, atracciones, tipo);
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
	
	public void setCosto() {
		double costoTotal = 0;
		for(Atraccion atr : this.atracciones)
			costoTotal += atr.getCosto();
		this.costo = costoTotal * ( 1 - (this.descuento/100));
	}
}
