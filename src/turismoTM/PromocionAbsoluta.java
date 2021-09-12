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
		return this.nombre;				
	}

}
