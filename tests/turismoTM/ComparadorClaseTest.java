package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ComparadorClaseTest {
	
	private Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();;
	private List<Promocion> promociones;
	private List<Producto> listaProductos;
	private ComparadorClase cClase;

	@Before
	public void setUp(){
		mapaAtracciones = LectorArchivos.cargarAtracciones("atracciones.in");
		promociones = LectorArchivos.cargarPromociones("promociones.in", mapaAtracciones);
		listaProductos = new ArrayList<>(mapaAtracciones.values());
		for(Promocion prod : promociones)
			listaProductos.add(prod);
		cClase = new ComparadorClase(); 
	}

	@Test
	public void quePrimeroOrdenaLasPromociones() {
		listaProductos.sort(cClase);
		assertTrue(listaProductos.get(0).esPromocion());
		assertFalse(listaProductos.get(listaProductos.size()-1).esPromocion());
	}

}
