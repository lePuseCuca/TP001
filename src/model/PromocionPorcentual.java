package model;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private double descuento;
	
	public PromocionPorcentual(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Tipo tipo, double descuento) throws ErrorDatosException {
		super(nombre, tipoPromo, atracciones, tipo);
		if(validarDatoNumerico(descuento)) {
			this.descuento = descuento;
			this.setCosto();
		}else
			throw new ErrorDatosException("Datos con valor negativo");
	}
	
	public double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;		
	}

	@Override
	public String toString() {
		String detalle = String.format("%-30s %n",nombre);
		for (Atraccion atr: atracciones)
			detalle += "+" + atr; 
		detalle += String.format("%30s \t$%.0f \t %s hs. %n", ">Subtotal (-" + String.format("%.0f", descuento) + "%):", this.getCosto(), tiempo);
		return detalle + "\n";
	}
	
	@Override
	public void setCosto() {
		super.setCosto();
		this.costo = Math.floor(this.costo * ( 1 - (this.descuento/100)));
	}
}
