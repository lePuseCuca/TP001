package dao;

import turismoTM.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	
	public abstract Usuario findUsuarioByNombre(String nombre);
	
}