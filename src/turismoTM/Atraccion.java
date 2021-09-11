package turismoTM;

public class Atraccion extends Producto {
	
	private int cupo;
	
	public Atraccion(String nombre, double costo, double tiempo, int cupo, Tipo tipo) {
		super(nombre, tipo);
		this.costo = costo;
		this.tiempo = tiempo; 
		this.tipo = tipo;
		this.cupo = cupo;
		
	}	
	
	protected int getCupo() {
		return this.cupo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}

	@Override
	public String toString() {
		return String.format("%-30s $%.0f \t %s hs. %n",nombre + " [" + tipo.getDescripcion() + "]", costo, tiempo);
	}
	
	@Override
	public double getCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCosto() {
		
	}

}
