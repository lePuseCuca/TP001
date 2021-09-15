package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionPorcentualTest {

	Atraccion atr1, atr2;
	List<Atraccion> atracciones = new ArrayList<Atraccion>();
	

	@Before
	public void setUp() throws Exception {
		atr1 = new Atraccion("Moria", 10, 2, 6, Tipo.AVENTURA);
		atr2 = new Atraccion("Mordor", 25, 3, 2, Tipo.AVENTURA);
		
		atracciones.add(atr1);
		atracciones.add(atr2);	
			
		
	}
	
	@Test
	public void queCreaUnaPromocion() throws ErrorDatosException {
		PromocionPorcentual promo = new PromocionPorcentual("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 20);
		assertNotNull(promo);
		assertEquals(5, promo.getTiempo(), 0);
		assertEquals(28, promo.getCosto(), 0);
	}
	
	@Test
	public void queDescuentaCupoAlVenderPromo() throws ErrorDatosException {
		PromocionPorcentual promo = new PromocionPorcentual("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 20);
		assertTrue(promo.venderProducto());
		assertEquals(5, atr1.getCupo());
		assertEquals(1, atr2.getCupo());
		
		
		assertTrue(promo.venderProducto());
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
		
	}
	
	@Test
	public void queNoVendePromoSiUnaAtraccionNoTieneCupo() throws ErrorDatosException {
		PromocionPorcentual promo = new PromocionPorcentual("Prueba", TipoPromocion.ABSOLUTA, atracciones, Tipo.AVENTURA, 20);
		assertTrue(promo.venderProducto());
		assertTrue(promo.venderProducto());
		assertFalse(promo.venderProducto());
		
		assertEquals(4, atr1.getCupo());
		assertEquals(0, atr2.getCupo());
		
	}


}
