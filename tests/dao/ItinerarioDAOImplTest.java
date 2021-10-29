package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import turismoTM.Itinerario;
import turismoTM.SecretariaTurismo;

public class ItinerarioDAOImplTest {

	private static Connection conn;
	SecretariaTurismo st = new SecretariaTurismo();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.setAutoCommit(true);
	}

	@After
	public void tearDown() throws Exception {
		conn.rollback();
	}

	@Test
	public void queCargaItinerarioPorUsuario() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		Itinerario itinerarioUsuarioA = gestorItinerario.findItinerarioByUsuario("Gandalf", st.getProductos());
		Itinerario itinerarioUsuarioB = gestorItinerario.findItinerarioByUsuario("Dumbledore", st.getProductos());
		assertEquals(gestorItinerario.countAll(), itinerarioUsuarioA.getTotalProductos(), 0);
		assertTrue(itinerarioUsuarioB == null);
		// itinerarioUsuarioA.mostrarItinerario();
	}

	@Test
	public void queCuentaItinerarios() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		assertTrue(gestorItinerario.countAll() > 0);
	}

	@Test
	public void queInsertaItinerario() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		Itinerario iti = new Itinerario("Sam");
		// iti.mostrarItinerario();
		iti.addProducto(st.getProductos().get("Erebor"));
		iti.addProducto(st.getProductos().get("Pack Sabores"));
		assertEquals(iti.getTotalProductos(), gestorItinerario.insert(iti), 0);
		// iti.mostrarItinerario();
	}

	@Test
	public void queActualizaItinerario() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		Itinerario iti = gestorItinerario.findItinerarioByUsuario("Gandalf", st.getProductos());
		iti.mostrarItinerario();
		int TotalProdOriginal = iti.getTotalProductos();
		iti.addProducto(st.getProductos().get("Moria"));
		gestorItinerario.update(iti);
		iti.mostrarItinerario();
		assertEquals(gestorItinerario.findItinerarioByUsuario("Gandalf", st.getProductos()).getTotalProductos(),
				TotalProdOriginal + 1, 0);
	}
}