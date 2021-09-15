package turismoTM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectorArchivos {
	
	public static List<Usuario> leerUsuarios(String archivo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario nuevoUsr = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				nuevoUsr = crearUsuario(linea);
				if (nuevoUsr != null)
					usuarios.add(nuevoUsr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return usuarios;
	}
	
	public static Map<String, Atraccion> cargarAtracciones(String archivo){
		FileReader fr = null;
		BufferedReader br = null;
		Map<String, Atraccion> destino = new HashMap<String, Atraccion>();
		Atraccion temp = null;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;			
			while ((linea = br.readLine()) != null) {				
				temp = crearAtraccion(linea);
				if (temp != null)
					destino.put(temp.getNombre(), temp);
			}				
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return destino;

	}

	public static List<Promocion> cargarPromociones(String archivo, Map<String, Atraccion> mapaAtracciones ) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Promocion> listaPromo = new ArrayList<Promocion>();
		Promocion temp = null;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;			
			while ((linea = br.readLine()) != null) {
				temp = crearPromocion(linea, mapaAtracciones);
				if (temp != null)
					listaPromo.add(temp);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return listaPromo;

	}

	
	
	
	/* ---- METODOS PRIVADOS  ----*/
	private static Usuario crearUsuario(String linea) {
		String[] datos = linea.split(";");
		Usuario nuevoUsuario = new Usuario();
		try {
			nuevoUsuario = new Usuario(datos[0], Double.parseDouble(datos[1]), 
						Double.parseDouble(datos[2]), Tipo.valueOf(datos[3]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ErrorDatosException e) {
			nuevoUsuario = null;
			System.err.println("Error de datos en el archivo de usuarios: " + e.getMessage() + ".\nAlgunos usuarios no se cargaron correctamente.");
		}
		
		return nuevoUsuario;
		
	}
	
	private static Atraccion crearAtraccion(String datos){
		String[] temp = datos.split(";");
		Atraccion nueva = null;
//		try {
			try {
				nueva = new Atraccion(
						temp[0], 
						Integer.parseInt(temp[1]), 
						Double.parseDouble(temp[2]),
						Integer.parseInt(temp[3]), 
						Tipo.valueOf(temp[4]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ErrorDatosException e) {
				System.err.println("Error en datos de atracciones: " + e.getMessage() + ".\nAlgunos productos no se cargaron correctamente.");
			} 

		return nueva;
		 
	}
	
	private static Promocion crearPromocion(String datos, Map<String, Atraccion> mapaAtracciones) {
		
		Promocion promoNueva = null;
		
		String[] promo = datos.split(";");
		String[] atracciones = promo[2].split(",");
		List<Atraccion> tempAt = new ArrayList<Atraccion>();
		boolean atraccionInexistente = false;
		for(String nombre : atracciones)
			if(mapaAtracciones.containsKey(nombre))
				tempAt.add(mapaAtracciones.get(nombre));
			else
				atraccionInexistente = true;
		
		switch (promo[0]) {
		case "PORCENTUAL":
			if(!atraccionInexistente) {
				try {
					promoNueva = new PromocionPorcentual(
							promo[1], 
							TipoPromocion.valueOf(promo[0]), 
							tempAt,
							Tipo.valueOf(promo[4]),
							Double.parseDouble(promo[3]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ErrorDatosException e) {
					System.err.println("Error en datos de promociones: " + e.getMessage() + ".\nAlgunos productos no se cargaron correctamente.");
				}
			}
			break;			
		case "ABSOLUTA":
			if(!atraccionInexistente) {
				try {
					promoNueva = new PromocionAbsoluta(
								promo[1], 
								TipoPromocion.valueOf(promo[0]), 
								tempAt,
								Tipo.valueOf(promo[4]),
								Double.parseDouble(promo[3]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ErrorDatosException e) {
					System.err.println("Error en datos de promociones: " + e.getMessage() + ".\nAlgunos productos no se cargaron correctamente.");
				}
			}
			break;
		case "AxB":			
			if(!atraccionInexistente && mapaAtracciones.containsKey(promo[3])) {
				promoNueva = new PromocionAxB(
						promo[1], 
						TipoPromocion.valueOf(promo[0]), 
						tempAt,
						mapaAtracciones.get(promo[3]),
						Tipo.valueOf(promo[4]));
			}
			break;
		}	
		return promoNueva;
	}
}

