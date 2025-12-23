package DAO;

import java.sql.SQLException;

import Modelos.Persona;

public interface PersonaDAO {

	  void guardarEnBD(Persona persona) throws SQLException;
}
