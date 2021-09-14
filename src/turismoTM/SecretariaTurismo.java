package turismoTM;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SecretariaTurismo {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Map<String, Atraccion> atracciones = new HashMap<String, Atraccion>();
	private List<Promocion> promociones = new ArrayList<Promocion>();
	private List<Producto> productos = new ArrayList<Producto>();

	public SecretariaTurismo() {
		this.usuarios = LectorArchivos.leerUsuarios("usuarios.in");
		atracciones = LectorArchivos.cargarAtracciones("atracciones.in");
		promociones = LectorArchivos.cargarPromociones("promociones.in", atracciones);
		setProductos();
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	// Metodo para sugerir productos
	public void sugerirProductos() throws IOException {
		double presupuestoCliente;
		double tiempoCliente;
		List<Producto> itinerario;
		//List<Producto> sugerenciasParaUsuario = new ArrayList<Producto>();
		Scanner sc = new Scanner(System.in);
		char respuesta;

		for (Usuario usr : usuarios) {
			presupuestoCliente = usr.getPresupuesto();
			tiempoCliente = usr.getTiempo();
			itinerario = new ArrayList<Producto>();
			Iterator<Producto> itr = getProductosParaUsuario(usr).iterator();

			 
			while ((presupuestoCliente > 0 && tiempoCliente > 0) && itr.hasNext()) {
				Producto sugerencia = itr.next();
				if (!atraccionComprada(sugerencia, itinerario)
						&& puedeComprar(sugerencia, presupuestoCliente, tiempoCliente)) {
					
					System.out.println(usr.getNombre() + " tu presupuesto es de: $" + presupuestoCliente
							+ " y dispones de " + tiempoCliente + "hs.");
					System.out.println("Deseas adquirir:");
					System.out.println(sugerencia);
					
					do{
						System.out.println("Presiona S o N.");
						respuesta = sc.next().charAt(0);
						if (respuesta == 'n')
							continue;
						if (respuesta == 's') {
							if (sugerencia.venderProducto()) {
								itinerario.add(sugerencia);
								presupuestoCliente -= sugerencia.getCosto();
								tiempoCliente -= sugerencia.getTiempo();
							}
						}
					}while(respuesta!='s' && respuesta!='n');
				}
			}
			mostrarItinerario(itinerario, usr);
			usr.comprarItinerario(calcularCostoItinerario(itinerario));
			guardarItinerario(itinerario, usr);
		}

		sc.close();

	}

	/*---- Metodos privados ----*/
	private void setProductos() {
		for (Promocion promo : this.promociones)
			productos.add(promo);
		for (String nombre : this.atracciones.keySet())
			productos.add(this.atracciones.get(nombre));
	}

	// Metodo que dado un usuario genera los comparadores y ordena lista de
	// productos en base a su preferencia.
	private List<Producto> getProductosParaUsuario(Usuario usr) {
		List<Producto> resultado = this.productos;		
		Collections.sort(resultado, generarComparadorProducto(usr.getTipoPreferido()));

		return resultado;
	}
	
	private ComparadorProducto generarComparadorProducto (Tipo tipoPreferido) {
		List<Comparator<Producto>> comparadores = new ArrayList<Comparator<Producto>>();
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

	private void guardarItinerario(List<Producto> itinerario, Usuario usr) throws FileNotFoundException {
		PrintWriter salida;
		salida = new PrintWriter(usr.getNombre() + ".out");
		salida.println("Itinerario para " + usr.getNombre());
		for (Producto prd : itinerario)
			salida.println(prd);
		salida.println("_________________________________________________");
		salida.println("COSTO TOTAL: $" + calcularCostoItinerario(itinerario) + " - Tiempo necesario: "
				+ calcularTiempoItinerario(itinerario) + " hs.");
		salida.close();

	}

	public void mostrarItinerario(List<Producto> itinerario, Usuario usr) {

		System.out.println("_________________________________________________");
		System.out.println("Itinerario para " + usr.getNombre());
		for (Producto prd : itinerario)
			System.out.print(prd);
		System.out.println("COSTO TOTAL: $" + calcularCostoItinerario(itinerario) + " - Tiempo necesario: "
				+ calcularTiempoItinerario(itinerario) + " hs.");
		System.out.println("_________________________________________________");

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
