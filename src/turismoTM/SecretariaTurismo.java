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
		this.usuarios = gestorUsuarios.findAll();
		atracciones = gestorAtracciones.findAllAtracciones();
		promociones = gestorPromociones.findAll(atracciones);
		setProductos();
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Map<String, Producto> getProductos() {
		return this.productos;
	}

	// Metodo para sugerir productos
	public void sugerirProductos() {
		
		double presupuestoCliente;
		double tiempoCliente;
		double costoCompra;
		double tiempoCompra;

		Scanner sc = new Scanner(System.in);
		char respuesta;

		for (Usuario usr : usuarios) {
			//Itinerario
			Itinerario itinerario = gestorItinerarios.findItinerarioByUsuario(usr.getNombre(), productos);

			if (itinerario == null)  new Itinerario(usr.getNombre());
					
			presupuestoCliente = usr.getPresupuesto();
			tiempoCliente = usr.getTiempo();
			costoCompra = 0;
			tiempoCompra = 0;
			
			Iterator<Producto> itr = getProductosParaUsuario(usr).iterator();

			while ((presupuestoCliente > 0 && tiempoCliente > 0) && itr.hasNext()) {
				Producto sugerencia = itr.next();

				if (!atraccionComprada(sugerencia, itinerario.getProductos())
						&& puedeComprar(sugerencia, presupuestoCliente, tiempoCliente)) {

					System.out.println(usr.getNombre() + ",\ntu presupuesto actual es de: $" + String.format("%.0f", presupuestoCliente)
							+ " y dispones de " + tiempoCliente + " hs.\n");
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

			mostrarItinerario(itinerario.getProductos(), usr);
			usr.comprarItinerario(costoCompra, tiempoCompra);
			try {
				guardarItinerario(itinerario);
				//itinerario.guardar()
			} catch (MissingDataException e) {
				System.err.print("El itinerario no se guardo correctamente");
			}

		}

		sc.close();

	}

	/*---- Metodos privados ----*/
	private void setProductos() {
		productos.putAll(atracciones);
		for (Promocion promo : this.promociones)
			productos.put(promo.getNombre(), promo);
	}

	// Metodo que dado un usuario genera los comparadores y ordena lista de
	// productos en base a su preferencia.
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

	private boolean atraccionComprada(Producto p, List<Producto> itinerario) {

		if (itinerario != null) {
			for (Producto producto : itinerario) {
				if (producto.esPromocion()) {
					for (Atraccion atr : producto.getAtracciones())
						if (p.equals(atr))
							return true;
				}
			}
		}
		return false;
	}

	private boolean puedeComprar(Producto prd, double presupuesto, double tiempo) {
		return (presupuesto >= prd.getCosto() && tiempo >= prd.getTiempo() && prd.hayCupo());
	}

	private void guardarItinerario(Itinerario itinerario) throws MissingDataException {
		if(itinerario.getNuevoItinerario())
			gestorItinerarios.insert(itinerario);
		else
			gestorItinerarios.update(itinerario);

	}

	public void mostrarItinerario(List<Producto> itinerario, Usuario usr) {

		if (itinerario.size() > 0) {
			System.out.println("________________________________________________________");
			System.out.println("Itinerario para " + usr.getNombre());
			for (Producto prd : itinerario)
				System.out.print(prd);
			System.out.println("COSTO TOTAL: $" + String.format("%.0f", calcularCostoItinerario(itinerario)) + " - Tiempo necesario: "
					+ calcularTiempoItinerario(itinerario) + " hs.");
			System.out.println("________________________________________________________");
		} else {
			System.out.println("___________________________________________");
			System.out.println(usr.getNombre() + ", tu itinerario esta vacio.");
			System.out.println("___________________________________________");
		}

	}

	private double calcularCostoItinerario(List<Producto> itinerario) {
		double costoItinerario = 0;
		if (itinerario != null) {
			for (Producto compra : itinerario)
				costoItinerario += compra.getCosto();
		}
		return costoItinerario;
	}

	private double calcularTiempoItinerario(List<Producto> itinerario) {
		double tiempoItinerario = 0;
		if (itinerario != null) {
			for (Producto compra : itinerario)
				tiempoItinerario += compra.getTiempo();
		}
		return tiempoItinerario;
	}
}
