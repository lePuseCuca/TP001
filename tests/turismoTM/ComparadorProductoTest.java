package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ComparadorProductoTest {

	private Map<String, Producto> mapaProductos = new HashMap<String, Producto>();;
	private List<Producto> listaProductos;	
	private ComparadorProducto comp;
	private List<Comparator<Producto>> comparadores;
	private Tipo preferido;
	
	@Before
	public void setUp() { 
		Tipo[] tipos = new Tipo[]{ Tipo.AVENTURA, Tipo.DEGUSTACION, Tipo.PAISAJE};
		/*Creamos lista de comparadores y los comparadores que va a contener en este orden: 
		*	TipoPreferido
		*	Clase
		*   Costo
		*   Tiempo
		*/
		
		//Elegimos un tipo preferido al azar de un arreglo de tipos, y se lo pasamos al comparador por tipo.
		Double random = Math.random()*3;
		preferido = tipos[random.intValue()];
		comparadores = new ArrayList<Comparator<Producto>>();
		comparadores.add(new ComparadorTipoPreferido(preferido));
		comparadores.add(new ComparadorClase());
		comparadores.add(new ComparadorCosto());
		comparadores.add(new ComparadorTiempo());
		comp = new ComparadorProducto(comparadores);
		
		LectorArchivos.cargarProductos("atracciones.in", mapaProductos);
		LectorArchivos.cargarProductos("promociones.in", mapaProductos);
	}

	@Test
	public void quePrimeroQuedanPreferidosYPromociones() {
		//System.out.println(preferido.getDescripcion());
		listaProductos = new ArrayList<Producto>(mapaProductos.values());
		listaProductos.sort(comp);
		assertTrue (listaProductos.get(0).getTipo() == preferido && listaProductos.get(0).esPromocion());
	}
	
	@Test
	public void queUltimoNoEsPreferidoNiPromocion() {
		//System.out.println(preferido.getDescripcion());
		listaProductos = new ArrayList<Producto>(mapaProductos.values());
		listaProductos.sort(comp);
		assertFalse (listaProductos.get(listaProductos.size()-1).getTipo() == preferido && listaProductos.get(listaProductos.size()-1).esPromocion());
	}

}
