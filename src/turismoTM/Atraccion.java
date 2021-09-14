package turismoTM;

import java.util.List;

public class Atraccion extends Producto {
	
	private int cupo;
	
	public Atraccion(String nombre, double costo, double tiempo, int cupo, Tipo tipo) {
		super(nombre, tipo);
		this.costo = costo;
		this.tiempo = tiempo; 
		this.tipo = tipo;
		this.cupo = cupo;
		
	}	
	
	public int getCupo() {
		return this.cupo;
	}
	
	@Override
	public String toString() {
		return String.format("%-30s $%.0f \t %s hs. %n",nombre + " [" + tipo.getDescripcion() + "]", costo, tiempo);		
	}
	
	public boolean restarCupo() {
		if (this.hayCupo()) {
			this.cupo -= 1;
			return true;
		}		
		return false;
	}

	protected boolean hayCupo() {
		return this.getCupo() > 0;
	}

	@Override
	protected List<Atraccion> getAtracciones() {
		return null;
	}

	@Override
	protected boolean venderProducto() {
		return this.restarCupo();
	}
}
