package DAO;

import java.sql.SQLException;

import Modelos.Usuario;

public interface UsuarioDAO {

	public void guardarEnBD(Usuario usuario) throws SQLException;
}
