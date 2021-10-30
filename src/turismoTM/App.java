package turismoTM;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		SecretariaTurismo st = new SecretariaTurismo();
		st.sugerirProductos();
	}
}