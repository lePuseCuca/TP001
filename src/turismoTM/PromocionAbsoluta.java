package turismoTM;

import java.util.List;

public class PromocionAbsoluta extends Promocion{
	public PromocionAbsoluta() {}
	
	public PromocionAbsoluta(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Tipo tipo, double costo) {
		super(nombre, tipoPromo, atracciones, tipo);
		this.costo = costo;		
	}
	
	@Override
	public String toString() {
		String detalle = String.format("%-32s %n",nombre);
		for (Atraccion atr: atracciones)
			detalle += "+" + atr; 
		detalle += String.format("%32s$%.0f \t %s hs. %n", ">Subtotal: ", this.getCosto(), tiempo);
		return detalle + "\n";			
	}

}
