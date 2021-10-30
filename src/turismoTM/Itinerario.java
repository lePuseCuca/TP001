package turismoTM;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private String idUsuario;
	private List<Producto> productos;
	private boolean primeraCompra = true;
	private List<String> nuevosProductos;
	// COBRAR SOLO LOS NUEVOS!!!
	
	public Itinerario (String idUsuario) {
		this.idUsuario = idUsuario;
		this.productos = new ArrayList<Producto>();
		this.nuevosProductos = new ArrayList<String>();
	}
	
	public void setNuevoItinerario() {
		this.primeraCompra = false;
	}
	
	public boolean getNuevoItinerario() {
		return this.primeraCompra;
	}
	
	public void addProducto(Producto producto) {
		this.productos.add(producto);
		if(!primeraCompra)
			this.nuevosProductos.add(producto.getNombre());
	}
	
	public List<Producto> getProductos(){
		return this.productos;
	}
	
	public List<String> getNuevosProductos(){
		return this.nuevosProductos;
	}
	
	public int getTotalProductos() {
		return this.productos.size();
	}
	
	public String getNombreUsuario () {
		return this.idUsuario;
	}	
	//toString()
	public void mostrarItinerario() {

		if (this.productos.size() > 0) {
			System.out.println("________________________________________________________");
			System.out.println("Itinerario para " + this.idUsuario);
			for (Producto prd : this.productos)
				System.out.print(prd);
			System.out.println("COSTO TOTAL: $" + String.format("%.0f", calcularCostoItinerario()) + " - Tiempo necesario: "
					+ calcularTiempoItinerario() + " hs.");
			System.out.println("________________________________________________________");
		} else {
			System.out.println("___________________________________________");
			System.out.println(this.idUsuario + ", tu itinerario esta vacio.");
			System.out.println("___________________________________________");
		}

	}

	private double calcularCostoItinerario() {
		double costoItinerario = 0;
		if (this.productos != null) {
			for (Producto compra : this.productos)
				costoItinerario += compra.getCosto();
		}
		return costoItinerario;
	}

	private double calcularTiempoItinerario() {
		double tiempoItinerario = 0;
		if (this.productos != null) {
			for (Producto compra : this.productos)
				tiempoItinerario += compra.getTiempo();
		}
		return tiempoItinerario;
	}
}