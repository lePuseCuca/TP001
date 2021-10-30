package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class PromocionAxBTest {

	Atraccion atr1, atr2, atr3;
	List<Atraccion> atracciones = new ArrayList<Atraccion>();
	PromocionAxB promo;

	@Before
	public void setUp() throws Exception {
		atr1 = new Atraccion("Moria", 10, 2, 6, Tipo.AVENTURA);
		atr2 = new Atraccion("Mordor", 25, 3, 2, Tipo.AVENTURA);
		atr3 = new Atraccion("Bosque Negro", 3, 4, 12, Tipo.AVENTURA);
		atracciones.add(atr1);
		atracciones.add(atr2);
		promo = new PromocionAxB("Prueba", TipoPromocion.AxB, atracciones, atr3, Tipo.AVENTURA);

	}

	@Test
	public void queCreaUnaPromocionYCalculaCostoTiempo() {
		
		assertNotNull(promo);
		assertEquals(9, promo.getTiempo(), 0);
		assertEquals(35, promo.getCosto(), 0.0);
	}

	@Test
	public void queDescuentaCupoAlVenderPromo() {
		
		assertTrue(promo.venderProducto());
		assertEquals(5, atr1.getCupo());
		assertEquals(1, atr2.getCupo());
		assertEquals(11, atr3.getCupo());

		assertTrue(promo.venderProducto());
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
		assertEquals(10, atr3.getCupo());
	}

	@Test
	public void queNoVendePromoSiUnaAtaccionNoTieneCupo() {
		
		assertTrue(promo.venderProducto());
		assertTrue(promo.venderProducto());
		assertFalse(promo.venderProducto());
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
		assertEquals(10, atr3.getCupo());
	}
	
}
