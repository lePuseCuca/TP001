package turismoTM;

import java.util.List;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionSinCargo; 
	
	public PromocionAxB(){}
	
	public PromocionAxB(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Atraccion atraccionSinCargo, Tipo tipo) {
		super(nombre, tipoPromo, atracciones, tipo);
		this.atraccionSinCargo = atraccionSinCargo;		
		this.tiempo += atraccionSinCargo.getTiempo();
		setCosto();
	}
	
	public String toString() {
		return this.getNombre();
	}	
	
	private void setCosto() {
		double costoTotal = 0;
		for(Atraccion atr : this.atracciones)
			costoTotal += atr.getCosto();
		this.costo = costoTotal;
	}

}
