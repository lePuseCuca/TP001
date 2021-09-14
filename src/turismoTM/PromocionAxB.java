package turismoTM;

import java.util.List;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionSinCargo; 
	
	public PromocionAxB(){}
	
	public PromocionAxB(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Atraccion atraccionSinCargo, Tipo tipo) {
		super(nombre, tipoPromo, atracciones, tipo);
		this.atraccionSinCargo = atraccionSinCargo;		
		this.tiempo += atraccionSinCargo.getTiempo();
		super.setCosto();
	}
	
	public String toString() {
		String detalle = String.format("%-32s %n",nombre);
		for (Atraccion atr: atracciones)
			detalle += "+" + atr; 
		detalle += String.format("+%-30s $%.0f \t %s hs. %n",atraccionSinCargo.getNombre() + " (SIN CARGO)", atraccionSinCargo.getCosto(), atraccionSinCargo.getTiempo());
		detalle += String.format("%30s \t$%.0f \t %s hs. %n", ">Subtotal:", this.getCosto(), tiempo);
		return detalle + "\n";
	}	
	
	@Override
	public List<Atraccion> getAtracciones(){
		List<Atraccion> temp = this.atracciones;
		temp.add(this.atraccionSinCargo);
		return temp;
	}

}
