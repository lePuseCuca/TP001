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
	
	
	@Override
	public String toString() {
		String resumen = "";
		if (this.productos.size() > 0) {
			resumen += "________________________________________________________\n" +
						"Itinerario para " + this.idUsuario + "\n";
			
			for (Producto prd : this.productos) resumen += prd.toString();
			
			resumen += "COSTO TOTAL: $" + String.format("%.0f", calcularCostoItinerario()) + " - Tiempo necesario: "
					+ calcularTiempoItinerario() + " hs.\n" +
					"________________________________________________________\n";
			
		} else {
			
			resumen += "___________________________________________\n" +
					this.idUsuario + ", tu itinerario esta vacio.\n" +
					"___________________________________________\n";
		}
		return resumen;
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