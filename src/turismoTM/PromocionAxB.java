package turismoTM;

import java.util.List;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionSinCargo; 
	
	public PromocionAxB(){}
	
	public PromocionAxB(String nombre, Tipo tipo, List<Atraccion> atracciones, Atraccion atraccionSinCargo) {
		super(nombre, tipo, atracciones);
		this.atraccionSinCargo = atraccionSinCargo;		
		this.tiempo += atraccionSinCargo.getTiempo();
		super.setCosto();
	}
	
	public String toString() {
		return this.getNombre();
	}	

}
