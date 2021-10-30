package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;

public class PromocionDAOImplTest {

	AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();
	Map<String, Atraccion> atracciones = atrDAO.findAllAtracciones();
	
	private static Connection conn;

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
	public void countAllTest() {
		PromocionDAO promocionDAO = new PromocionDAOImpl();
		int total = promocionDAO.countAll();
		assertEquals(3, total);
	}
	
	@Test
	public void findAllTest() {
		PromocionDAO promocionDAO = new PromocionDAOImpl();
		List<Promocion> promociones = promocionDAO.findAll(atracciones);
		assertEquals(promocionDAO.countAll(), promociones.size());
	}
}