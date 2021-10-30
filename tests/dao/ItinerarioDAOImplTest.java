package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.Producto;
import model.Promocion;

public class ItinerarioDAOImplTest {

	private static Connection conn;
	private static Map<String, Producto> productos;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Map<String, Atraccion> mapaAtracciones = new AtraccionesDAOImpl().findAllAtracciones();
		List<Promocion> promociones = new PromocionDAOImpl().findAll(mapaAtracciones);
		productos = new HashMap<String, Producto>();
		productos.putAll(mapaAtracciones);
		for(Promocion promo : promociones)
			productos.put(promo.getNombre(), promo);
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
		Itinerario itiAux = new Itinerario("Gandalf");
		itiAux.addProducto(productos.get("Erebor"));
		gestorItinerario.insert(itiAux);
		Itinerario itinerarioUsuarioA = gestorItinerario.findItinerarioByUsuario("Gandalf", productos);
		Itinerario itinerarioUsuarioB = gestorItinerario.findItinerarioByUsuario("Dumbledore", productos);
		assertEquals(gestorItinerario.countAll(), itinerarioUsuarioA.getTotalProductos(), 0);
		assertTrue(itinerarioUsuarioB == null);
		// itinerarioUsuarioA.mostrarItinerario();
	}

	@Test
	public void queCuentaItinerarios() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		Itinerario itiAux = new Itinerario("Gandalf");
		itiAux.addProducto(productos.get("Erebor"));
		gestorItinerario.insert(itiAux);
		assertTrue(gestorItinerario.countAll() > 0);
	}

	@Test
	public void queInsertaItinerario() {
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		Itinerario iti = new Itinerario("Sam");
		// iti.mostrarItinerario();
		iti.addProducto(productos.get("Erebor"));
		iti.addProducto(productos.get("Pack Sabores"));
		assertEquals(iti.getTotalProductos(), gestorItinerario.insert(iti), 0);
		// iti.mostrarItinerario();
	}

	@Test
	public void queActualizaItinerario() {
		Itinerario itiAux = new Itinerario("Gandalf");
		itiAux.addProducto(productos.get("Erebor"));
		ItinerarioDAO gestorItinerario = DAOFactory.getItinerarioDAO();
		gestorItinerario.insert(itiAux);
		Itinerario iti = gestorItinerario.findItinerarioByUsuario("Gandalf", productos);
		int TotalProdOriginal = iti.getTotalProductos();
		iti.addProducto(productos.get("Moria"));
		gestorItinerario.update(iti);
		assertEquals(gestorItinerario.findItinerarioByUsuario("Gandalf", productos).getTotalProductos(),
				TotalProdOriginal + 1, 0);
	}
}