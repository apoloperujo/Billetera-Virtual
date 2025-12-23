package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Java.MyConnection;
import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;
import Modelos.Moneda;

public class CriptoUsuarioDAOjdbc implements CriptoUsuarioDAO {
	
	
	public boolean guardarEnBD(CriptoUsuario criptoUsuario) throws SQLException {
	    Connection connection = MyConnection.getCon();
	    String nomenclatura = criptoUsuario.getMoneda().getNomenclatura();
	    double cantidad = criptoUsuario.getCantidad();

	    // Verificar si la cripto ya existe para el usuario específico
	    String checkSql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
	    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
	        checkStmt.setString(1, nomenclatura);
	        checkStmt.setInt(2, criptoUsuario.getID_Usuario());
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            // La nomenclatura ya existe para este usuario, actualizar la cantidad
	            double cantidadExistente = rs.getDouble("CANTIDAD");
	            double nuevaCantidad = cantidadExistente + cantidad;

	            String updateSql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
	            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
	                updateStmt.setDouble(1, nuevaCantidad);
	                updateStmt.setString(2, nomenclatura);
	                updateStmt.setInt(3, criptoUsuario.getID_Usuario());
	                updateStmt.executeUpdate();
	            }
	        } else {
	            // La nomenclatura no existe para este usuario, realizar la inserción
	            int ID_MONEDA = MonedaDAOjdbc.obtenerIDMoneda(nomenclatura);
	            String insertSql = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO, ID_MONEDA, NOMENCLATURA, CANTIDAD) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	                insertStmt.setInt(1, criptoUsuario.getID_Usuario());
	                insertStmt.setInt(2, ID_MONEDA);
	                insertStmt.setString(3, nomenclatura);
	                insertStmt.setDouble(4, cantidad);
	                insertStmt.executeUpdate();
	            }
	        }
	        return true; // Se guardó correctamente
	    } catch (SQLException e) {
	        System.out.println("Error al guardar en la base de datos: " + e.getMessage());
	        return false; // Ocurrió un error al guardar
	    }
	}
	
	public List<CriptoUsuario> listarActivosCripto() throws SQLException {
	    List<CriptoUsuario> listaCriptoUsuarios = new ArrayList<>();
	    Connection connection = MyConnection.getCon();
	    String sql = "SELECT * FROM ACTIVO_CRIPTO";
	    MonedaDAO monedaDAO = DAOFactory.getMonedaDAO();

	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            int id = rs.getInt("ID");
	            int idUsuario = rs.getInt("ID_USUARIO");
	            int idMoneda = rs.getInt("ID_MONEDA");
	            double cantidad = rs.getDouble("CANTIDAD");
	            String nomenclatura = rs.getString("NOMENCLATURA");

	            // Obtener el valor en dólares de la moneda
	            double valorDolar = monedaDAO.obtenerPrecioMoneda(nomenclatura);
	            Moneda moneda = new Moneda( valorDolar);

	            // Crear instancia de CriptoUsuario con Moneda incluida
	            CriptoUsuario criptoUsuario = new CriptoUsuario(cantidad, moneda);
	            criptoUsuario.setID(id);
	            criptoUsuario.setID_Usuario(idUsuario);
	            criptoUsuario.setID_Moneda(idMoneda);
	            criptoUsuario.setNomenclatura(nomenclatura);

	            listaCriptoUsuarios.add(criptoUsuario);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al listar los activos cripto: " + e.getMessage());
	    }
	    return listaCriptoUsuarios;
	}



	
	public CriptoUsuario obtenerCriptoPorNomenclatura(String nomenclatura) throws SQLException {
	    CriptoUsuario criptoUsuario = null; // Inicializamos como null, si no se encuentra el registro, se devuelve null.

	    Connection connection = MyConnection.getCon();

	    // Consulta SQL para obtener solo los datos de la moneda con la nomenclatura dada
	    String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, nomenclatura); // Asignamos el valor de la nomenclatura a la consulta

	        try (ResultSet rs = pstmt.executeQuery()) {
	            // Si hay resultados, los asignamos a la instancia de FiduciariaUsuario
	            if (rs.next()) {
	                int id = rs.getInt("ID");
	                int idUsuario = rs.getInt("ID_USUARIO");
	                int idMoneda = rs.getInt("ID_MONEDA");
	                double cantidad = rs.getDouble("CANTIDAD");
	                
	                // Creamos un objeto FiduciariaUsuario con los datos obtenidos
	                criptoUsuario = new CriptoUsuario(cantidad, nomenclatura, idUsuario, id, idMoneda);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener la fiduciaria por nomenclatura: " + e.getMessage());
	    }

	    return criptoUsuario; // Devolvemos el objeto o null si no se encontró
	}
    
    
    public boolean Existe(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT NOMENCLATURA FROM ACTIVO_CRIPTO WHERE TRIM(UPPER(NOMENCLATURA)) = TRIM(UPPER(?))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia en la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    public double obtenerStockCriptoUsuario(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("CANTIDAD");
            } else {
                System.out.println("Error: No tienes " + nomenclatura + " en tu billetera.");
                return 0;
            }
        }
    }
    
    public boolean modificarCantidadCriptoUsuario(String nomenclatura, double nuevaCantidad) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevaCantidad);
            pstmt.setString(2, nomenclatura);
            
            // Ejecutar la actualización y verificar cuántas filas se han afectado
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                return true; // Se actualizó correctamente
            } else {
                System.out.println("Error: No se encontró la criptomoneda con nomenclatura " + nomenclatura);
                return false; // No se encontró la criptomoneda
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar la cantidad en la base de datos: " + e.getMessage());
            return false; // Ocurrió un error al intentar modificar
        }
    }
    
}
