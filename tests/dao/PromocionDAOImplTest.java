package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import turismoTM.Atraccion;
import turismoTM.LectorArchivos;
import turismoTM.Promocion;

public class PromocionDAOImplTest {

	Map<String, Atraccion> atracciones = LectorArchivos.cargarAtracciones("atracciones.in");
	
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
	public void findAllTest() {
		//usar size() + countAll()
		PromocionDAO promocionDAO = new PromocionDAOImpl();
		
		List<Promocion> promociones = promocionDAO.findAll(atracciones);
		
		System.out.println(promociones);
	}

}



















