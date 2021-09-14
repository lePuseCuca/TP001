package turismoTM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectorArchivos {
	
	public static List<Usuario> leerUsuarios(String archivo) {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null)
				usuarios.add(crearUsuario(linea));
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
	
	public static Map<String, Atraccion> cargarAtracciones(String archivo) {
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
		return new Usuario(datos[0], Double.parseDouble(datos[1]), 
					Double.parseDouble(datos[2]), Tipo.valueOf(datos[3]));
		
	}
	
	private static Atraccion crearAtraccion(String datos){
		String[] temp = datos.split(";");
		return (new Atraccion(
					temp[0], 
					Integer.parseInt(temp[1]), 
					Double.parseDouble(temp[2]),
					Integer.parseInt(temp[3]), 
					Tipo.valueOf(temp[4]))
				);
	}
	
	private static Promocion crearPromocion(String datos, Map<String, Atraccion> mapaAtracciones) {
		
		Promocion promoNueva = null;
		
		String[] promo = datos.split(";");
		String[] atracciones = promo[2].split(",");
		List<Atraccion> tempAt = new ArrayList<Atraccion>();

		switch (promo[0]) {
		case "PORCENTUAL":
			for (String ID : atracciones) {
				tempAt.add(mapaAtracciones.get(ID));
			}
			promoNueva = new PromocionPorcentual(
						promo[1], 
						TipoPromocion.valueOf(promo[0]), 
						tempAt,
						Tipo.valueOf(promo[4]),
						Double.parseDouble(promo[3]));	
			break;			
		case "ABSOLUTA":
			for (String ID : atracciones) {
				tempAt.add(mapaAtracciones.get(ID));
			}
			promoNueva = new PromocionAbsoluta(
						promo[1], 
						TipoPromocion.valueOf(promo[0]), 
						tempAt,
						Tipo.valueOf(promo[4]),
						Double.parseDouble(promo[3]));
			break;
		case "AxB":
			for (String ID : atracciones) {
				tempAt.add(mapaAtracciones.get(ID));
			}
			promoNueva = new PromocionAxB(
					promo[1], 
					TipoPromocion.valueOf(promo[0]), 
					tempAt,
					mapaAtracciones.get(promo[3]),
					Tipo.valueOf(promo[4]));	
			break;
		}	
		return promoNueva;
	}
}

