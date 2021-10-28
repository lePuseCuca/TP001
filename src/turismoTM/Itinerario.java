package turismoTM;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private String idUsuario;
	private List<Producto> productos;
	private boolean nuevoItinerario = true;
	private List<String> nuevosProductos;
	// COBRAR SOLO LOS NUEVOS!!!
	
	public Itinerario (String idUsuario) {
		this.idUsuario = idUsuario;
		this.productos = new ArrayList<Producto>();
		this.nuevosProductos = new ArrayList<String>();
	}
	
	public void setNuevoItinerario() {
		this.nuevoItinerario = false;
	}
	
	public boolean getNuevoItinerario() {
		return this.nuevoItinerario;
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
	
	public boolean getNuevoItinerario() {
		return this.nuevoItinerario;
	}
	
	
}

/*
 * PrintWriter salida;
		salida = new PrintWriter(usr.getNombre() + ".out");

		if (itinerario.size() > 0) {
			
			salida.println("Itinerario para " + usr.getNombre());
			for (Producto prd : itinerario)
				salida.println(prd);
			salida.println("________________________________________________________");
			salida.println("COSTO TOTAL: $" + String.format("%.0f", calcularCostoItinerario(itinerario)) + " - Tiempo necesario: "
					+ calcularTiempoItinerario(itinerario) + " hs.");
		} else {
			salida.println("___________________________________________");
			salida.println(usr.getNombre() + ", tu itinerario esta vacio.");
			salida.println("___________________________________________");
		}
		
		salida.close();*/
