package turismoTM;

import java.util.List;

public class Promocion extends Producto{

	protected List<Atraccion> atracciones;
	private TipoPromocion tipoPromocion;
	
	public Promocion() {};
	
	public Promocion(String nombre, TipoPromocion tipoPromo, List<Atraccion> atracciones, Tipo tipo) {
		super(nombre, tipo);
		this.atracciones = atracciones;
		this.tiempo = setTiempo(atracciones);		
		this.tipoPromocion = tipoPromo;
	}
	
	
	
	private double setTiempo(List<Atraccion> atracciones) {
		double tiempoTotal = 0;
		
		for (Atraccion at: atracciones)
			tiempoTotal += at.getTiempo();
		
		return tiempoTotal;
	}	
	
}
