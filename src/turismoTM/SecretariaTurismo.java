package turismoTM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecretariaTurismo {
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Map<String, Atraccion> atracciones = new HashMap<String, Atraccion>();
	private List<Promocion> promociones = new ArrayList<Promocion>();
	private List<Producto> productos = new ArrayList<Producto>();

	// Preguntar donde cargar archivos (App?)
	public SecretariaTurismo() {
		this.usuarios = LectorArchivos.leerUsuarios("usuarios.in");
		atracciones = LectorArchivos.cargarAtracciones("atracciones.in");
		promociones = LectorArchivos.cargarPromociones("promociones.in", atracciones);
		setProductos();
	}
	
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public List<Producto> getProductos(){
		return this.productos;
	}
	
	private void setProductos() {
		for (Promocion promo: this.promociones) 
			productos.add(promo);
		for (String nombre : this.atracciones.keySet()) 
			productos.add(this.atracciones.get(nombre));
	}
}
