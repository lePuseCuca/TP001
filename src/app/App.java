package app;

import java.io.IOException;

import model.SecretariaTurismo;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		SecretariaTurismo st = new SecretariaTurismo();
		st.sugerirProductos();
	}
}