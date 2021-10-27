package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import turismoTM.Atraccion;
import turismoTM.ErrorDatosException;
import turismoTM.Itinerario;
import turismoTM.Producto;
import turismoTM.PromocionAbsoluta;
import turismoTM.PromocionAxB;
import turismoTM.PromocionPorcentual;
import turismoTM.Tipo;
import turismoTM.TipoPromocion;


public class ItinerarioDAOImplTest {

	private static Connection conn;
	private Map<String, Producto> productos = this.getProductos();
	private ItinerarioDAOImpl gestorItinerario = new ItinerarioDAOImpl();

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
	public void tearDown() throws Exception{
		conn.rollback();
	}

	@Test
	public void queCargaItinerarioPorUsuario() {		
		try{
			 
			Itinerario itinerarioUsuarioA = gestorItinerario.findItinerarioByUsuario("Gandalf", this.productos);
			//Si el usuario no tiene itinerario devuelve null
			Itinerario itinerarioUsuarioB = gestorItinerario.findItinerarioByUsuario("Dumbledore", this.productos);
			assertEquals(3, itinerarioUsuarioA.getTotalProductos(), 0);
			assertTrue(itinerarioUsuarioB == null);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void queCuentaItinerarios() {
		try {
			assertTrue(gestorItinerario.countAll() > 0);
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Test
	public void queInsertaItinerario() {
		Itinerario iti = new Itinerario("Sam");
		iti.addProducto(productos.get("Erebor"));
		iti.addProducto(productos.get("Pack Sabores"));
		
		assertEquals(iti.getTotalProductos(), gestorItinerario.insert(iti), 0);
	}
	
	@Test
	public void queActualizaItinerario() {
		Itinerario iti = gestorItinerario.findItinerarioByUsuario("Gandalf", productos);
		int prodOriginal = iti.getTotalProductos();
		iti.addProducto(productos.get("Erebor"));
		gestorItinerario.update(iti);		
		
		assertEquals(gestorItinerario.findItinerarioByUsuario("Gandalf", productos).getTotalProductos(),
				prodOriginal + 1,
				0);
	}
	
	private Map<String, Producto> getProductos(){
		Map<String, Producto> auxProd = new HashMap<String, Producto>();
		
		
		try {
			//Carga de atracciones
			auxProd.put("Moria", new Atraccion ("Moria",10,2,6,Tipo.valueOf("AVENTURA")));
			auxProd.put("Minas Tirith", new Atraccion ("Minas Tirith",5,2.5,25,Tipo.valueOf("PAISAJE")));
			auxProd.put("La Comarca", new Atraccion ("La Comarca",3,6.5,150,Tipo.valueOf("DEGUSTACION")));
			auxProd.put("Mordor", new Atraccion ("Mordor",25,3,4,Tipo.valueOf("AVENTURA")));
			auxProd.put("Lothlórien", new Atraccion ("Lothlórien",35,1,30,Tipo.valueOf("DEGUSTACION")));
			auxProd.put("Erebor", new Atraccion ("Erebor",12,3,32,Tipo.valueOf("PAISAJE")));
			auxProd.put("Bosque Negro", new Atraccion ("Bosque Negro",3,4,12,Tipo.valueOf("AVENTURA")));
			auxProd.put("Abismo de Helm", new Atraccion ("Abismo de Helm",5,2,15,Tipo.valueOf("PAISAJE")));
			//Carga de promociones
			Atraccion[] a = {(Atraccion) auxProd.get("Moria"), (Atraccion) auxProd.get("Bosque Negro")};
			auxProd.put("Pack Aventura", 
					new PromocionPorcentual ("Pack Aventura",
							TipoPromocion.PORCENTUAL, 
							new ArrayList<Atraccion>(Arrays.asList(a)),
							Tipo.AVENTURA,
							20)
					);
			Atraccion[] b = {(Atraccion) auxProd.get("Lothlórien"), (Atraccion) auxProd.get("La Comarca")};
			auxProd.put("Pack Sabores", 
					new PromocionAbsoluta ("Pack Sabores",
							TipoPromocion.ABSOLUTA, 
							new ArrayList<Atraccion>(Arrays.asList(b)),
							Tipo.DEGUSTACION,
							36)
					);
			Atraccion[] c = {(Atraccion) auxProd.get("Minas Tirith"), (Atraccion) auxProd.get("Abismo de Helm")};
			auxProd.put("Pack Paisajes", 
					new PromocionAxB ("Pack Paisajes",
							TipoPromocion.AxB, 
							new ArrayList<Atraccion>(Arrays.asList(c)),
							(Atraccion) auxProd.get("Erebor"),
							Tipo.PAISAJE)
					);
		
		} catch (ErrorDatosException e) {
			e.printStackTrace();
		}
				
				
		return auxProd;
	}

}
