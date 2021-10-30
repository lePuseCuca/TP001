package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	private Usuario u;

	@Before
	public void setUp() throws Exception {
		u = new Usuario("Tom Bombadil", 100, 25, Tipo.AVENTURA);
	}

	@Test
	public void creaUnUsuarioTest() {
		assertNotNull(u);
	}

	/*
	 * @Test public void queDescuentaPresupuestoAlPagarItinerario() {
	 * assertEquals(100, u.getPresupuesto(), 0.0); u.comprarItinerario(50);
	 * assertEquals(50, u.getPresupuesto(), 0.0); }
	 */

	@Test(expected = ErrorDatosException.class)
	public void queArrojaExcepcionSiPresupuestoNegativo() throws ErrorDatosException {
		@SuppressWarnings("unused")
		Usuario u2 = new Usuario("Bono", -100, 25, Tipo.AVENTURA);
	}

	@Test(expected = ErrorDatosException.class)
	public void queArrojaExcepcionSiTiempoNegativo() throws ErrorDatosException {
		@SuppressWarnings("unused")
		Usuario u3 = new Usuario("Raistlin Majere", 100, -25, Tipo.PAISAJE);
	}

}
