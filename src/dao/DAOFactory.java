package dao;

public class DAOFactory {
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static ItinerarioDAOImpl getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}

}