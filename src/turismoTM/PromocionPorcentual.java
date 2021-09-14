package turismoTM;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private double descuento;
	
	public PromocionPorcentual(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Tipo tipo, double descuento) {
		super(nombre, tipoPromo, atracciones, tipo);
		this.descuento = descuento;
		this.setCosto();
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
		super.setCosto();
		this.costo = Math.floor(this.costo * ( 1 - (this.descuento/100)));
	}
}
