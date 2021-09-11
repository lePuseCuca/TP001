package turismoTM;

import java.util.List;

public class Promocion extends Producto{

	protected List<Atraccion> atracciones;
	
	public Promocion() {};
	
	public Promocion(String nombre, Tipo tipo, List<Atraccion> atracciones) {
		super(nombre, tipo);		
		this.tiempo = setTiempo(atracciones);		
		this.atracciones = atracciones;
	}
	
	
	
	private double setTiempo(List<Atraccion> atracciones) {
		double tiempoTotal = 0;
		
		for (Atraccion at: atracciones)
			tiempoTotal += at.getTiempo();
		
		return tiempoTotal;
	}

	@Override
	public void setCosto() {
		int costoTotal = 0;
		
		for(Atraccion at: this.atracciones)
			costoTotal += at.getCosto();
		
		this.costo = costoTotal;
	}
}
