package turismoTM;

public class Usuario {
	private String nombre;
	private double monedas;
	private double tiempo;
	private Tipo tipoPreferido;
	
	public Usuario() {}
	
	public Usuario(String nombre, double monedas, double tiempo, Tipo tipoPreferido) throws ErrorDatosException {
		
		if (validarDato(monedas) && validarDato(tiempo)){
			this.nombre = nombre;
			this.monedas = monedas;
			this.tiempo = tiempo;
			this.tipoPreferido = tipoPreferido;
		}else
			throw new ErrorDatosException("Datos con valor negativo");
		
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
		if (costoItinerario <= this.monedas) {
			this.monedas -= costoItinerario;
			return true;
		}
		
		return false;
	}
	
	private boolean validarDato(double dato) {
		return (dato > 0);
	}	
}
