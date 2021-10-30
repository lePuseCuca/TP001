package model;

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
	
	public List<Atraccion> getAtracciones(){
		return this.atracciones;
	}
	
	@Override
	public boolean esPromocion() {
		return true;
	}
	
	public TipoPromocion getTipoPromocion() {
		return this.tipoPromocion;
	}
	
	protected void setCosto() {
		double costoTotal = 0;
		for(Atraccion atr : atracciones)
			costoTotal += atr.getCosto();
		this.costo = costoTotal;
	}
	
	protected boolean hayCupo() {
		for (Atraccion atr: atracciones)
			if (atr.getCupo() <= 0 )
				return false;
		return true;
	}
	
	private double setTiempo(List<Atraccion> atracciones) {
		double tiempoTotal = 0;		
		for (Atraccion at: atracciones)
			tiempoTotal += at.getTiempo();		
		return tiempoTotal;
	}

	@Override
	protected boolean venderProducto() {
		boolean vendible = true;
		for (Atraccion atr: this.atracciones)
			if (atr.getCupo() == 0)
				vendible = false;
		if (vendible)
			for (Atraccion atr: this.atracciones)
				atr.restarCupo();
		return vendible;
	}	
	
		
	
}
