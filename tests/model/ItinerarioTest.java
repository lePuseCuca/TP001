package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItinerarioTest {

	private Itinerario iti;
	private Atraccion atr1, atr2;
	
	@Before
	public void setUp() {
		try {
			iti = new Itinerario("Saruman");
			atr1 = new Atraccion("Gondor", 20, 3, 50, Tipo.PAISAJE);
			atr2 = new Atraccion("Rohan", 50, 5, 10, Tipo.AVENTURA);
		} catch (ErrorDatosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void queCreaItinerario() {		
		assertNotNull(iti);
	}
	
	@Test
	public void queCreaItinerarioNuevo() {
		assertTrue(iti.getNuevoItinerario());
	}
	
	@Test
	public void queAgregaProductos(){
		iti.addProducto(atr1);
		assertEquals(1, iti.getProductos().size(), 0);	
		assertEquals("Gondor", iti.getProductos().get(0).getNombre());
	}
	
	@Test
	public void queControlaNuevosProductos(){
		iti.setPrimeraCompraFalso();
		iti.addProducto(atr1);
		assertEquals(1, iti.getNuevosProductos().size(), 0);
	}
	
	@Test
	public void queAgregaYControlaProductosNuevos() {
		iti.addProducto(atr1);
		assertEquals(0, iti.getNuevosProductos().size(), 0);
		assertEquals(1, iti.getProductos().size(), 0);
		iti.setPrimeraCompraFalso();
		iti.addProducto(atr2);
		assertEquals(1, iti.getNuevosProductos().size(), 0);
		assertEquals(2, iti.getProductos().size(), 0);		
	}
}
