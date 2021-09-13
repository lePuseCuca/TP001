package turismoTM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecretariaTurismo {
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Map<String, Producto> productos = new HashMap<String, Producto>();

	public SecretariaTurismo() {
		this.usuarios = LectorArchivos.leerUsuarios("usuarios.in");
		LectorArchivos.cargarProductos("atracciones.in", productos);
		LectorArchivos.cargarProductos("promociones.in", productos);
	}
	
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public Map<String, Producto> getProductos(){
		return this.productos;
	}
}
