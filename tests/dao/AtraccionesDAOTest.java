package dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import turismoTM.Atraccion;
import turismoTM.ErrorDatosException;
import turismoTM.Tipo;

public class AtraccionesDAOTest {

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
	public void queCuentaLasAtracciones() throws ErrorDatosException {
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		int numeroDeAtracciones = atrDAO.countAll();
		int numeroDeAtraccionesEsperado = 8;
		assertEquals(numeroDeAtraccionesEsperado, numeroDeAtracciones, 0);
	}

	@Test
	public void queInsertaUnaAtraccion() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		int numeroDeAtraccionesInicial = atrDAO.countAll();
		atrDAO.insert(atr);
		assertEquals(numeroDeAtraccionesInicial + 1, atrDAO.countAll(), 0);
	}
	
	@Test
	public void queEncuentraUnaAtraccionPorNombre() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();
		
		atrDAO.insert(atr);
		Atraccion atrDB = atrDAO.findAtraccionByNombre("Prueba");
	
		assertEquals("Prueba", atrDB.getNombre());
		assertEquals(10, atrDB.getCosto(), 0);
		assertEquals(20, atrDB.getTiempo(), 0);
		assertEquals(30, atrDB.getCupo());
		assertEquals(Tipo.AVENTURA, atrDB.getTipo());
	}

	@Test
	public void queActualizaUnaAtraccion() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		atrDAO.insert(atr);
		atr.restarCupo();
		atrDAO.update(atr);
		int cupoInicial = 30;
		int cupoFinal = cupoInicial - 1;
		Atraccion atrDB = atrDAO.findAtraccionByNombre("Prueba");
		assertEquals(cupoFinal, atrDB.getCupo());
	}
	
	@Test
	public void queEliminaUnaAtraccion() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		int numeroDeAtraccionesInicial = atrDAO.countAll();
		atrDAO.insert(atr);
		assertEquals(numeroDeAtraccionesInicial + 1, atrDAO.countAll(), 0);
		
		atrDAO.delete(atr);
		assertEquals(numeroDeAtraccionesInicial, atrDAO.countAll(), 0);
	}
	
	@Test
	public void queDevuelveTodasLasAtracciones() throws ErrorDatosException {
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();
		assertEquals(atrDAO.findAll().size(), atrDAO.countAll());
	}

}
