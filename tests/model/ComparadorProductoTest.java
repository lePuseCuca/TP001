package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionesDAOImpl;
import dao.PromocionDAOImpl;


public class ComparadorProductoTest {

	private Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
	private List<Promocion> promociones;
	private List<Producto> listaProductos;	
	private ComparadorProducto comp;
	private List<Comparator<Producto>> comparadores;
	private Tipo preferido;
	
	@Before
	public void setUp() { 
		Tipo[] tipos = new Tipo[]{ Tipo.AVENTURA, Tipo.DEGUSTACION, Tipo.PAISAJE};
		//Elegimos un tipo preferido al azar de un arreglo de tipos, y se lo pasamos al comparador por tipo.
		Double random = Math.random()*3;
		preferido = tipos[random.intValue()];

		/*Creamos lista de comparadores y los comparadores que va a contener en este orden: 
		*	TipoPreferido
		*	Clase
		*   Costo
		*   Tiempo
		*/
		comparadores = new ArrayList<Comparator<Producto>>();
		comparadores.add(new ComparadorTipoPreferido(preferido));
		comparadores.add(new ComparadorClase());
		comparadores.add(new ComparadorCosto());
		comparadores.add(new ComparadorTiempo());
		comp = new ComparadorProducto(comparadores);
		
		mapaAtracciones = new AtraccionesDAOImpl().findAllAtracciones();
		promociones = new PromocionDAOImpl().findAll(mapaAtracciones);
		
	}

	@Test
	public void quePrimeroQuedanPreferidosYPromociones() {
		//System.out.println(preferido.getDescripcion());
		this.cargarListaProductos();
		listaProductos.sort(comp);
		assertTrue (listaProductos.get(0).getTipo() == preferido && listaProductos.get(0).esPromocion());
	}
	
	@Test
	public void queUltimoNoEsPreferidoNiPromocion() {
		this.cargarListaProductos();
		listaProductos.sort(comp);
		assertFalse (listaProductos.get(listaProductos.size()-1).getTipo() == preferido && listaProductos.get(listaProductos.size()-1).esPromocion());
	}
	
	private void cargarListaProductos() {
		listaProductos = new ArrayList<Producto>(this.mapaAtracciones.values());
		for(Promocion promo : promociones)
			listaProductos.add(promo);
			
	}

}
