package dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import turismoTM.Tipo;
import turismoTM.Usuario;

public class TestUsuarioDao {

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
	public void testInsertarUsuarioYCountAll() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		try {
			int total = userDAO.countAll();
			userDAO.insert(new Usuario("Carlos", 10, 20, Tipo.AVENTURA));
			assertEquals(total + 1, userDAO.countAll());
	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testEncontrarUsuarioPorNombre() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		try {
			userDAO.findUsuarioByNombre("Sam");
						
			assertEquals("Sam", userDAO.findUsuarioByNombre("Sam").getNombre());
			assertEquals(36, userDAO.findUsuarioByNombre("Sam").getPresupuesto(), 001);
			assertEquals(8, userDAO.findUsuarioByNombre("Sam").getTiempo(), 001);
			assertEquals(Tipo.valueOf("DEGUSTACION"), userDAO.findUsuarioByNombre("Sam").getTipoPreferido());
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void testUpdateUsuarioMonedas() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		try {
			Usuario usuario = userDAO.findUsuarioByNombre("Sam");
			double presupuestoOriginal = userDAO.findUsuarioByNombre("Sam").getPresupuesto();
					
			double costoItinerario = 15;
			usuario.comprarItinerario(costoItinerario);
						
			userDAO.update(usuario);
			double presupuestoActualizado = userDAO.findUsuarioByNombre("Sam").getPresupuesto();
					
			assertEquals(presupuestoOriginal - costoItinerario, presupuestoActualizado, 0);
										
		} catch (Exception e) {
			System.out.println("mal");
			e.printStackTrace();
		}

	}
	@Test
	public void borrarUsuario() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		try {
			Usuario usuario = userDAO.findUsuarioByNombre("Galadriel");
			assertEquals("Galadriel", usuario.getNombre());
			userDAO.delete(usuario);
			
			usuario = userDAO.findUsuarioByNombre("Galadriel");			
			assertTrue(usuario == null);

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	
}
