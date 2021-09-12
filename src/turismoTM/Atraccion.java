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
	
	@Override
	public String toString() {
		return String.format("%-30s $%.0f \t %s hs. %n",nombre + " [" + tipo.getDescripcion() + "]", costo, tiempo);
	}
}
