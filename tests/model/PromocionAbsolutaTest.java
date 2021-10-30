package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionAbsolutaTest {

	Atraccion atr1, atr2, atr3;
	List<Atraccion> atracciones = new ArrayList<Atraccion>();
	

	@Before
	public void setUp() throws Exception {
		atr1 = new Atraccion("Moria", 10, 4, 6, Tipo.AVENTURA);
		atr2 = new Atraccion("Mordor", 25, 3, 2, Tipo.AVENTURA);
		
		atracciones.add(atr1);
		atracciones.add(atr2);	
			
		
	}
	
	@Test
	public void queCreaUnaPromocion() throws ErrorDatosException {
		PromocionAbsoluta promo = new PromocionAbsoluta("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 30);
		assertNotNull(promo);
		assertEquals(7, promo.getTiempo(), 0);
		assertEquals(30, promo.getCosto(), 0);
	}
	
	@Test
	public void queDescuentaCupoAlVenderPromo() throws ErrorDatosException {
		PromocionAbsoluta promo = new PromocionAbsoluta("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 35);
		assertTrue(promo.venderProducto());
		assertEquals(5, atr1.getCupo());
		assertEquals(1, atr2.getCupo());
	
		
		assertTrue(promo.venderProducto());
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
	
	}
	
	@Test
	public void queNoVendePromoSiUnaAtraccionNoTieneCupo() throws ErrorDatosException {
		PromocionAbsoluta promo = new PromocionAbsoluta("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 35);
		assertTrue(promo.venderProducto());
		assertTrue(promo.venderProducto());
		assertFalse(promo.venderProducto());
		
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
	
	}

}
