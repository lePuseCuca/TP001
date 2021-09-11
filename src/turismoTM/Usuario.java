package turismoTM;

public class Usuario {
	private String nombre;
	private double monedas;
	private double tiempo;
	private Tipo tipoPreferido;
	
	public Usuario(String nombre, double monedas, double tiempo, Tipo tipoPreferido) {
		this.nombre = nombre;
		this.monedas = monedas;
		this.tiempo = tiempo;
		this.tipoPreferido = tipoPreferido;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public double getPresupuesto() {
		return this.monedas;
	}
	
	public double getTiempo() {
		return this.tiempo;
	}
	
	public Tipo getTipoPreferido() {
		return this.tipoPreferido;
	}
	
	public boolean comprarItinerario(double costoItinerario) {
		return false;
	}
}
