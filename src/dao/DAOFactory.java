package dao;

public class DAOFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static AtraccionesDAO getAtraccionesDAO() {
		return new AtraccionesDAOImpl();
	}
	
}