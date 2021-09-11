package turismoTM;

import java.util.List;

public class PromocionAbsoluta extends Promocion{
	public PromocionAbsoluta() {}
	
	public PromocionAbsoluta(String nombre, Tipo tipo, List<Atraccion> atracciones, double costo) {
		super(nombre, tipo, atracciones);
		this.costo = costo;		
	}
	
	@Override
	public String toString() {
		return this.nombre;				
	}

}
