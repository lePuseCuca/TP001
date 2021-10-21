package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import turismoTM.ErrorDatosException;
import turismoTM.Tipo;
import turismoTM.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario findUsuarioByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Usuario toUsuario(ResultSet resultados) throws SQLException, ErrorDatosException {
		return new Usuario(resultados.getString("nombre"), 
				resultados.getDouble("presupuesto"),
				resultados.getDouble("tiempo"),
				Tipo.valueOf(resultados.getString("tipo_preferido")));
	}

}
