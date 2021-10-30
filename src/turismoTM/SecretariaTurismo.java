package turismoTM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.MissingDataException;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class SecretariaTurismo {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Map<String, Atraccion> atracciones = new HashMap<String, Atraccion>();
	private List<Promocion> promociones = new LinkedList<Promocion>();
	private Map<String, Producto> productos = new HashMap<String, Producto>();
	private UsuarioDAO gestorUsuarios = DAOFactory.getUsuarioDAO();
	private AtraccionesDAO gestorAtracciones = DAOFactory.getAtraccionesDAO();
	private PromocionDAO gestorPromociones = DAOFactory.getPromocionDAO();
	private ItinerarioDAO gestorItinerarios = DAOFactory.getItinerarioDAO();

	public SecretariaTurismo() {
		this.usuarios = this.gestorUsuarios.findAll();
		this.atracciones = this.gestorAtracciones.findAllAtracciones();
		this.promociones = this.gestorPromociones.findAll(atracciones);
		setProductos();
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Map<String, Producto> getProductos() {
		return this.productos;
	}

	public void sugerirProductos() throws InterruptedException {
		double presupuestoCliente;
		double tiempoCliente;
		double costoCompra;
		double tiempoCompra;

		Scanner sc = new Scanner(System.in);
		char respuesta;

		for (Usuario usr : this.usuarios) {
			Itinerario itinerario = this.gestorItinerarios.findItinerarioByUsuario(usr.getNombre(), this.productos);
			
			if (itinerario == null) itinerario = new Itinerario(usr.getNombre());
			
			presupuestoCliente = usr.getPresupuesto();
			tiempoCliente = usr.getTiempo();
			costoCompra = 0;
			tiempoCompra = 0;

			Iterator<Producto> itr = this.getProductosParaUsuario(usr).iterator();

			while ((presupuestoCliente > 0 && tiempoCliente > 0) && itr.hasNext()) {

				Producto sugerencia = itr.next();

				if (!atraccionComprada(sugerencia, itinerario.getProductos())
						&& puedeComprar(sugerencia, presupuestoCliente, tiempoCliente)) {

					System.out.println("________________________________________________________");
					System.out.println(usr.getNombre() + ",\ntu presupuesto actual es de: $"
							+ String.format("%.0f", presupuestoCliente) + " y dispones de " + tiempoCliente + " hs.\n");
					System.out.println("Deseas adquirir:");
					System.out.println(sugerencia);

					do {
						System.out.println("Presiona S o N.");
						respuesta = sc.next().charAt(0);
						if (respuesta == 'n' || respuesta == 'N')
							continue;
						if (respuesta == 's' || respuesta == 'S') {
							if (sugerencia.venderProducto()) {
								itinerario.addProducto(sugerencia);
								presupuestoCliente -= sugerencia.getCosto();
								tiempoCliente -= sugerencia.getTiempo();
								costoCompra += sugerencia.getCosto();
								tiempoCompra += sugerencia.getTiempo();
							}
						}
					} while (respuesta != 's' && respuesta != 'S' && respuesta != 'N' && respuesta != 'n');
				}
			}
			if(costoCompra == 0) {
				System.out.println(usr.getNombre() + " no realizó ninguna compra.");
				System.out.print("Cargando itinerario anterior.");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".");
				Thread.sleep(500);
				System.out.print(".\n");
				Thread.sleep(500);
			}
			System.out.println(itinerario);
			if (usr.comprarItinerario(costoCompra, tiempoCompra))
				try {
					this.gestorUsuarios.update(usr);
					guardarItinerario(itinerario); // itinerario.guardar()
					
					
				} catch (MissingDataException e) {
					System.err.print("El itinerario no se guardo correctamente");
				}
		}
		sc.close();
		
	}

	private void setProductos() {
		this.productos.putAll(this.atracciones);
		for (Promocion promo : this.promociones)
			this.productos.put(promo.getNombre(), promo);
	}

	/*
	 * Recibe un usuario y devuelve una lista ordenada de productos según
	 * suspreferencias.
	 */
	private List<Producto> getProductosParaUsuario(Usuario usr) {
		List<Producto> resultado = new LinkedList<Producto>(this.productos.values());
		Collections.sort(resultado, generarComparadorProducto(usr.getTipoPreferido()));
		return resultado;
	}

	private ComparadorProducto generarComparadorProducto(Tipo tipoPreferido) {
		List<Comparator<Producto>> comparadores = new LinkedList<Comparator<Producto>>();
		comparadores.add(new ComparadorTipoPreferido(tipoPreferido));
		comparadores.add(new ComparadorClase());
		comparadores.add(new ComparadorCosto());
		comparadores.add(new ComparadorTiempo());

		return (new ComparadorProducto(comparadores));
	}

	private boolean atraccionComprada(Producto p, List<Producto> productosItinerario) {
		for (Producto producto : productosItinerario) {
			if (p.equals(producto)) return true;
			if (producto.esPromocion()) {
				for (Atraccion atr : producto.getAtracciones())
					if (p.equals(atr)) return true;
			}
		}
		return false;
	}

	private boolean puedeComprar(Producto prd, double presupuesto, double tiempo) {
		return (presupuesto >= prd.getCosto() && tiempo >= prd.getTiempo() && prd.hayCupo());
	}

	private void guardarItinerario(Itinerario itinerario) throws MissingDataException {
		if (itinerario.getNuevoItinerario()) {
			this.gestorItinerarios.insert(itinerario);					
		if (!itinerario.getNuevosProductos().isEmpty())
			this.gestorItinerarios.update(itinerario);
		}
		
		for (Atraccion atr : this.atracciones.values())
			gestorAtracciones.update(atr);

	}
}