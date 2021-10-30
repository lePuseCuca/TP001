package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionesDAOImpl;
import dao.PromocionDAOImpl;

public class ComparadorCostoTest {
	
	private Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();;
	private List<Promocion> promociones;
	private List<Producto> listaProductos;
	private ComparadorCosto cCosto;

	@Before
	public void setUp(){
		mapaAtracciones = new AtraccionesDAOImpl().findAllAtracciones();
		promociones = new PromocionDAOImpl().findAll(mapaAtracciones);
		listaProductos = new ArrayList<>(mapaAtracciones.values());
		for(Promocion prod : promociones )
			listaProductos.add(prod);
		cCosto = new ComparadorCosto(); 
		
	}

	@Test
	public void queOrdenaPorCosto() {
		listaProductos.sort(cCosto);
		assertTrue(listaProductos.get(0).getCosto() > listaProductos.get(3).getCosto());
		assertTrue(listaProductos.get(5).getCosto() < listaProductos.get(1).getCosto());
	}
	
	@Test
	public void queMasCostosoQuedaPrimero() {
		double masCostoso = getMasCostoso(listaProductos);
		listaProductos.sort(cCosto);
		assertEquals(masCostoso, listaProductos.get(0).getCosto(), 0.0);
	}
	
	@Test
	public void queMenosCostosoQuedaUltimo() {
		double menosCostoso = getMenosCostoso(listaProductos);
		listaProductos.sort(cCosto);
		assertEquals(menosCostoso, listaProductos.get(listaProductos.size()-1).getCosto(), 0.0);
	}
	
	private double getMasCostoso(List<Producto> listaDesordenada) {
		double masCostoso = listaDesordenada.get(0).getCosto();
		for(int i = 1; i < listaDesordenada.size(); i++)
			if (listaDesordenada.get(i).getCosto() > masCostoso)
				masCostoso = listaDesordenada.get(i).getCosto();
		return masCostoso;
	}
	
	private double getMenosCostoso(List<Producto> listaDesordenada) {
		double menosCostoso = listaDesordenada.get(0).getCosto();
		for(int i = 1; i < listaDesordenada.size(); i++)
			if (listaDesordenada.get(i).getCosto() < menosCostoso)
				menosCostoso = listaDesordenada.get(i).getCosto();
		return menosCostoso;
	}

}
