package DAO;
import java.sql.SQLException;
import java.util.List;

import Modelos.FiduciariaUsuario;
public interface FiduciariaUsuarioDAO {

		/**
	     * Guarda un objeto FiduciariaUsuario en la base de datos.
	     * 
	     * @param connection La conexión a la base de datos.
	     * @param FiduciariaUsuario El objeto FiduciariaUsuario a guardar.
	     * @return true si el objeto se guardó correctamente, false en caso contrario.
	     * @throws SQLException en caso de que ocurra un error en la operación.
	     */
	    boolean guardarEnBD(FiduciariaUsuario fiduciariaUsuario) throws SQLException;
		
		boolean modificarCantidadFiatUsuario(String nomenclatura, double nuevaCantidad) throws SQLException;
		
		double obtenerStockFiatUsuario(String nomenclatura) throws SQLException;
		
		boolean Existe(String nomenclatura) throws SQLException;
		
		List<FiduciariaUsuario> listarActivosFiat() throws SQLException;
}