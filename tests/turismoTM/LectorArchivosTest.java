package turismoTM;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LectorArchivosTest {
	
	private List<Usuario> usuarios;
	private Map<String, Atraccion> atracciones = new HashMap<String, Atraccion>(); 
	private List<Promocion> promociones;
	
	
	@Before
	public void setUp() {
		usuarios = LectorArchivos.leerUsuarios("usuarios.in");
		atracciones = LectorArchivos.cargarAtracciones("atracciones.in");
		promociones = LectorArchivos.cargarPromociones("promociones.in", atracciones);
	}
	
	@Test
	public void queCargaUsuarios() {
		assertTrue(usuarios.size() > 0);
		assertEquals("Eowyn", usuarios.get(0).getNombre());
	}
	
	@Test
	public void queCargaAtracciones() {
		
		assertTrue(atracciones.size() > 0);
	}
	
	@Test
	public void queCargaPromociones() {
		assertTrue(promociones.size() > 0);
	}

}
