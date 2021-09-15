package turismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionAxBTest {
	
	/*Moria;10;2;6;AVENTURA
	Minas Tirith;5;2.5;25;PAISAJE
	La Comarca;3;6.5;150;DEGUSTACION
	Mordor;25;3;4;AVENTURA
	Abismo de Helm;5;2;15;PAISAJE
	Lothlórien;35;1;30;DEGUSTACION
	Erebor;12;3;32;PAISAJE
	Bosque Negro;3;4;12;AVENTURA*/
	Atraccion atr1, atr2, atr3;
	List<Atraccion> atracciones = new ArrayList<Atraccion>();
	

	@Before
	public void setUp() throws Exception {
		atr1 = new Atraccion("Moria", 10, 2, 6, Tipo.AVENTURA);
		atr2 = new Atraccion("Mordor", 25, 3, 4, Tipo.AVENTURA);
		atr3 = new Atraccion("Bosque Negro", 3, 4, 12, Tipo.AVENTURA);
		atracciones.add(atr1);
		atracciones.add(atr2);		
		
	}

	@Test
	public void queCreaUnaPromocion() {
		PromocionAxB promo = new PromocionAxB("Prueba", TipoPromocion.AxB, atracciones, atr3, Tipo.AVENTURA);
		assertNotNull(promo);
		assertEquals(9, promo.getTiempo(), 0);
	}

}
