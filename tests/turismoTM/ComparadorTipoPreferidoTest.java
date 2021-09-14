package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ComparadorTipoPreferidoTest {

	private Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
	private List<Promocion> promociones;
	private List<Producto> listaProductos;
	private Tipo preferido; 
	private ComparadorTipoPreferido cPreferido;
	
	@Before
	public void setUp(){
		mapaAtracciones = LectorArchivos.cargarAtracciones("atracciones.in");
		promociones = LectorArchivos.cargarPromociones("promociones.in", mapaAtracciones);
		listaProductos = new ArrayList<>(mapaAtracciones.values());
		for(Promocion promo : promociones)
			listaProductos.add(promo);
		Tipo[] tipos = new Tipo[]{ Tipo.AVENTURA, Tipo.DEGUSTACION, Tipo.PAISAJE};
		Double random = Math.random()*tipos.length;
		preferido = tipos[random.intValue()];
	}

	@Test
	public void queTipoPreferidoQuedaPrimero() {
		cPreferido = new ComparadorTipoPreferido(preferido); 
		listaProductos.sort(cPreferido);
		assertEquals(preferido, listaProductos.get(0).getTipo());		
	}
	
	@Test
	public void queNoPreferidoQuedaUltimo() {
		cPreferido = new ComparadorTipoPreferido(preferido); 
		listaProductos.sort(cPreferido);
		assertNotEquals(preferido, listaProductos.get(listaProductos.size()-1).getTipo());		
	}

}
