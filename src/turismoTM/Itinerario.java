package turismoTM;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private String idUsuario;
	private List<Producto> productos;
	private boolean nuevoItinerario = true;
	private List<String> nuevosProductos;
	// List<string> losnuevos;
	// Controlar nuevos aca.
	
	public Itinerario (String idUsuario) {
		this.idUsuario = idUsuario;
		this.productos = new ArrayList<Producto>();
		this.nuevosProductos = new ArrayList<String>();
	}
	
	public void setNuevoItinerario(boolean esNuevo) {
		this.nuevoItinerario = esNuevo;
	}
	
	public void addProducto(Producto producto) {
		this.productos.add(producto);
		if(!nuevoItinerario)
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
	
	
}
