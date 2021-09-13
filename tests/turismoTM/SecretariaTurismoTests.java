package turismoTM;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SecretariaTurismoTests {

	private SecretariaTurismo st;
	
	@Before
	public void setUp() {
		st = new SecretariaTurismo();
	}
	
	@Test
	public void queCargaUsuarios() {
		assertTrue(st.getUsuarios().size() > 0);
	}
	
	@Test
	public void queCargaProductos() {
		assertTrue(st.getProductos().size() > 0);
	}

}
