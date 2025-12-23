package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelos.CriptoUsuario;

public interface CriptoUsuarioDAO {
    
    /**
     * Guarda un objeto CriptoUsuario en la base de datos.
     * 
     * @param connection La conexión a la base de datos.
     * @param criptoUsuario El objeto CriptoUsuario a guardar.
     * @return true si el objeto se guardó correctamente, false en caso contrario.
     * @throws SQLException en caso de que ocurra un error en la operación.
     */
    boolean guardarEnBD( CriptoUsuario criptoUsuario) throws SQLException;
    
    boolean modificarCantidadCriptoUsuario(String nomenclatura, double nuevaCantidad) throws SQLException;
    
    boolean Existe(String nomenclatura) throws SQLException;
    
    List<CriptoUsuario> listarActivosCripto() throws SQLException;
    
    
}