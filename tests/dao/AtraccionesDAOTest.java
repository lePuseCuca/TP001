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
	public static void tearDown() throws Exception {
		conn.rollback();
	}

	@Test
	public void queCuentaLasAtracciones() throws ErrorDatosException {
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		int numeroDeAtraccionesEsperado = atrDAO.countAll();
		int numeroDeAtracciones = 8;
		assertEquals(numeroDeAtraccionesEsperado, numeroDeAtracciones, 0);
	}

	@Test
	public void queInsertaUnaAtraccion() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();

		int numeroDeAtraccionesEsperado = atrDAO.countAll();
		atrDAO.insert(atr);
		assertEquals(numeroDeAtraccionesEsperado, atrDAO.countAll(), 0);
	}
	
	@Test
	public void queEncuentraUnaAtraccionPorNombre() throws ErrorDatosException {
		Atraccion atr = new Atraccion("Prueba", 10, 20, 30, Tipo.AVENTURA);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionesDAO();
		
		atrDAO.insert(atr);
		Atraccion atrDB = atrDAO.findAtraccionByNombre("Prueba");
	
		assertEquals(atrDB.getNombre(), "Prueba");
		assertEquals(atrDB.getCosto(), 10, 0);
		assertEquals(atrDB.getTiempo(), 20, 0);
		assertEquals(atrDB.getCupo(), 30);
		assertEquals(atrDB.getTipo(), Tipo.AVENTURA);
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
		assertEquals(atrDB.getCupo(), cupoFinal);
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
