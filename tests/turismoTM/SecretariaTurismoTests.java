package turismoTM;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class SecretariaTurismoTests {

	private SecretariaTurismo st;

	@Before
	public void setUp() {
		st = new SecretariaTurismo();
	}

	@Test
	public void queCargaUsuarios() {
		UsuarioDAO gestorUsuarios = DAOFactory.getUsuarioDAO();
		assertEquals(gestorUsuarios.countAll(), st.getUsuarios().size(), 0);
	}

	@Test
	public void queCargaProductos() {

		AtraccionesDAO gestorAtracciones = DAOFactory.getAtraccionesDAO();
		PromocionDAO gestorPromociones = DAOFactory.getPromocionDAO();
		int expected = gestorAtracciones.countAll() + gestorPromociones.countAll();
		assertEquals(expected, st.getProductos().size(), 0);
	}

}
