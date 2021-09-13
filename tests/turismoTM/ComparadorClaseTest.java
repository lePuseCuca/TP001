package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ComparadorClaseTest {
	
	private Map<String, Producto> mapaProductos = new HashMap<String, Producto>();
	private List<Producto> listaProductos;
	private ComparadorClase cClase;

	@Before
	public void setUp(){
		LectorArchivos.cargarProductos("atracciones.in", mapaProductos);
		LectorArchivos.cargarProductos("promociones.in", mapaProductos);
		listaProductos = new ArrayList<>(mapaProductos.values());
		cClase = new ComparadorClase(); 
	}

	@Test
	public void quePrimeroOrdenaLasPromociones() {
		listaProductos.sort(cClase);
		assertTrue(listaProductos.get(0).esPromocion());
		assertFalse(listaProductos.get(listaProductos.size()-1).esPromocion());
	}

}
