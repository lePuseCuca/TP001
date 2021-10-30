package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class AtraccionTest {
	
	@Test
	public void queCreaUnaAtraccion() throws ErrorDatosException {
		Atraccion atr1 = new Atraccion("Moria", 10, 2, 3, Tipo.AVENTURA);
		assertNotNull(atr1);
		assertEquals(2, atr1.getTiempo(), 0);
		assertEquals(10, atr1.getCosto(), 0);
	}
	
	@Test
	public void queDescuentaCupoAlVenderAtraccion() throws ErrorDatosException {
		Atraccion atr1 = new Atraccion("Moria", 10, 2, 3, Tipo.AVENTURA);
		assertTrue(atr1.venderProducto());
		assertEquals(2, atr1.getCupo());
		
		assertTrue(atr1.venderProducto());
		assertEquals(1, atr1.getCupo());
	}
	
	@Test
	public void queNoVendeAtraccionSiNoTieneCupo() throws ErrorDatosException {
		Atraccion atr1 = new Atraccion("Moria", 10, 2, 3, Tipo.AVENTURA);
		assertTrue(atr1.venderProducto());
		assertTrue(atr1.venderProducto());
		assertTrue(atr1.venderProducto());
		assertFalse(atr1.venderProducto());
		
		assertEquals(0, atr1.getCupo());
	}
}
