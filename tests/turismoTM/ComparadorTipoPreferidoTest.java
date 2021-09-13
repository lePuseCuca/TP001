package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ComparadorTipoPreferidoTest {

	private Map<String, Producto> mapaProductos = new HashMap<String, Producto>();
	private List<Producto> listaProductos;
	private ComparadorTipoPreferido cPreferido;
	
	@Before
	public void setUp(){
		LectorArchivos.cargarProductos("atracciones.in", mapaProductos);
		LectorArchivos.cargarProductos("promociones.in", mapaProductos);
		listaProductos = new ArrayList<>(mapaProductos.values());		 
	}

	@Test
	public void queTipoPreferidoQuedaPrimero() {
		cPreferido = new ComparadorTipoPreferido(Tipo.AVENTURA); 
		listaProductos.sort(cPreferido);
		assertEquals(Tipo.AVENTURA, listaProductos.get(0).getTipo());		
	}
	
	@Test
	public void queNPreferidoQuedaUltimo() {
		cPreferido = new ComparadorTipoPreferido(Tipo.AVENTURA); 
		listaProductos.sort(cPreferido);
		assertNotEquals(Tipo.AVENTURA, listaProductos.get(listaProductos.size()-1).getTipo());		
	}

}
