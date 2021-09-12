package turismoTM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	public static void cargarProductos(String archivo, Map<String, Producto> destino) {
		FileReader fr = null;
		BufferedReader br = null;
		Producto temp = null;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;			
			while ((linea = br.readLine()) != null) {
				switch (archivo) {
					case "atracciones.in":
						temp = crearAtraccion(linea);
						break;
					case "promociones.in":
						temp = crearPromocion(linea, destino);
				}
				
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
	
	private static Producto crearPromocion(String datos, Map<String, Producto> destino) {
		String[] promo = datos.split(";");
		String[] atracciones = promo[2].split(",");
		List<Atraccion> tempAt = new ArrayList<Atraccion>();

		switch (promo[0]) {
		case "PORCENTUAL":
			for (String ID : atracciones) {
				tempAt.add((Atraccion) destino.get(ID));
			}
			return (new PromocionPorcentual(
						promo[1], 
						TipoPromocion.valueOf(promo[0]), 
						tempAt,
						Tipo.valueOf(promo[4]),
						Double.parseDouble(promo[3]))
					);
			
		case "ABSOLUTA":
			for (String ID : atracciones) {
				tempAt.add((Atraccion) destino.get(ID));
			}
			return (new PromocionAbsoluta(
						promo[1], 
						TipoPromocion.valueOf(promo[0]), 
						tempAt,
						Tipo.valueOf(promo[4]),
						Double.parseDouble(promo[3]))
					);
		case "AxB":
			for (String ID : atracciones) {
				tempAt.add((Atraccion) destino.get(ID));
			}
			return (new PromocionAxB(
					promo[1], 
					TipoPromocion.valueOf(promo[0]), 
					tempAt,
					(Atraccion) destino.get(promo[3]),
					Tipo.valueOf(promo[4]))					
				);
						
		}	
		return null;
	}
}

