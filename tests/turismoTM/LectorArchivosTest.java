package turismoTM;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LectorArchivosTest {
	
	private List<Usuario> usuarios;
	private Map<String, Producto> productos = new HashMap<String, Producto>();
	
	@Before
	public void setUp() {
		usuarios = LectorArchivos.leerUsuarios("usuarios.in");		
	}
	
	@Test
	public void queCargaUsuarios() {
		assertTrue(usuarios.size() > 0);
		assertEquals("Eowyn", usuarios.get(0).getNombre());
	}
	
	@Test
	public void queCargaAtracciones() {
		LectorArchivos.cargarProductos("atracciones.in", productos);
		assertTrue(productos.size() > 0);
	}
	
	@Test
	public void queCargaPromociones() {
		LectorArchivos.cargarProductos("atracciones.in", productos);
		int tamanioConAtracciones = productos.size();
		LectorArchivos.cargarProductos("promociones.in", productos);
		assertTrue(productos.size() > tamanioConAtracciones);
		assertTrue(productos.containsKey("Pack Aventura"));
		assertTrue(productos.containsKey("Pack Sabores"));
		assertTrue(productos.containsKey("Pack Paisajes"));
	}

}
