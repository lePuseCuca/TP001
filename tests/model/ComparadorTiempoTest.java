package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionesDAOImpl;
import dao.PromocionDAOImpl;

public class ComparadorTiempoTest {
	
	private Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();;
	private List<Promocion> promociones;
	private List<Producto> listaProductos;
	private ComparadorTiempo cTiempo;

	@Before
	public void setUp(){
		mapaAtracciones = new AtraccionesDAOImpl().findAllAtracciones();
		promociones = new PromocionDAOImpl().findAll(mapaAtracciones);
		listaProductos = new ArrayList<>(mapaAtracciones.values());
		for(Promocion prod : promociones)
			listaProductos.add(prod);
		cTiempo = new ComparadorTiempo(); 
	}

	@Test
	public void queOrdenaPorTiempo() {
		listaProductos.sort(cTiempo);
		assertTrue(listaProductos.get(0).getTiempo() > listaProductos.get(3).getTiempo());		
	}
	
	@Test
	public void queElMasLargoQuedaPrimero() {
		double masLargo = this.getMasLargo(listaProductos);
		listaProductos.sort(cTiempo);
		assertEquals(masLargo, listaProductos.get(0).getTiempo(), 0.0);
	}
	
	@Test
	public void queElMasCortoQuedaUltimo() {
		double masCorto = this.getMasCorto(listaProductos);
		listaProductos.sort(cTiempo);
		assertEquals(masCorto, listaProductos.get(listaProductos.size()-1).getTiempo(), 0.0);
	}
	
	private double getMasLargo(List<Producto> listaDesordenada) {
		double masLargo = listaDesordenada.get(0).getTiempo();
		for(int i = 1; i < listaDesordenada.size(); i++)
			if (listaDesordenada.get(i).getTiempo() > masLargo)
				masLargo = listaDesordenada.get(i).getTiempo();
		return masLargo;
	}
	
	private double getMasCorto(List<Producto> listaDesordenada) {
		double masCorto = listaDesordenada.get(0).getTiempo();
		for(int i = 1; i < listaDesordenada.size(); i++)
			if (listaDesordenada.get(i).getTiempo() < masCorto)
				masCorto = listaDesordenada.get(i).getTiempo();
		return masCorto;
	}

}
