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

public class FiduciariaUsuarioDAOjdbc implements FiduciariaUsuarioDAO{
	

	public boolean guardarEnBD(FiduciariaUsuario fiduciariaUsuario) throws SQLException {
		Connection connection= MyConnection.getCon();
	    String nomenclatura = fiduciariaUsuario.getMoneda().getNomenclatura();
	    double cantidad = fiduciariaUsuario.getCantidad();

	    
	    // Verificar si la nomenclatura ya existe
	    String checkSql = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
	    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
	        checkStmt.setString(1, nomenclatura);
	        ResultSet rs = checkStmt.executeQuery();
	        
	        if (rs.next()) {
	            // La nomenclatura ya existe, actualizar la cantidad
	            double cantidadExistente = rs.getDouble("CANTIDAD");
	            double nuevaCantidad = cantidadExistente + cantidad;
	            
	            String updateSql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
	            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
	                updateStmt.setDouble(1, nuevaCantidad);
	                updateStmt.setString(2, nomenclatura);
	                updateStmt.executeUpdate();
	            }
	        } else {
	            // La nomenclatura no existe, realizar la inserción
	        	int ID_MONEDA=MonedaDAOjdbc.obtenerIDMoneda(nomenclatura);
	            String insertSql = "INSERT INTO ACTIVO_FIAT (ID_USUARIO , ID_MONEDA ,NOMENCLATURA, CANTIDAD) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	                insertStmt.setInt(1, fiduciariaUsuario.getID_Usuario());
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
	
	public List<FiduciariaUsuario> listarActivosFiat() throws SQLException {
	    List<FiduciariaUsuario> listaFiduciariaUsuario = new ArrayList<>();

	    Connection connection = MyConnection.getCon();

	    // Consulta SQL con JOIN para obtener la nomenclatura y el valor en dólares desde la tabla MONEDA
	    String sql = "SELECT AF.ID, AF.ID_USUARIO, AF.ID_MONEDA, AF.CANTIDAD, AF.NOMENCLATURA, M.VALOR_DOLAR " +
	                 "FROM ACTIVO_FIAT AF " +
	                 "JOIN MONEDA M ON AF.ID_MONEDA = M.ID";

	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        // Recorre los resultados e inserta en la lista2
	        while (rs.next()) {
	            int id = rs.getInt("ID");
	            int idUsuario = rs.getInt("ID_USUARIO");
	            int idMoneda = rs.getInt("ID_MONEDA");
	            double cantidad = rs.getDouble("CANTIDAD");

	            String nomenclatura = rs.getString("NOMENCLATURA");
	            double valorDolar = rs.getDouble("VALOR_DOLAR");

	            // Crear el objeto Moneda
	            Moneda moneda = new Moneda(valorDolar);

	            // Pasar el objeto Moneda al constructor de FiduciariaUsuario
	            FiduciariaUsuario fiduciariaUsuario = new FiduciariaUsuario(cantidad, moneda, idUsuario, id, idMoneda, nomenclatura);

	            listaFiduciariaUsuario.add(fiduciariaUsuario);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al listar los activos fiat: " + e.getMessage());
	    }

	    return listaFiduciariaUsuario;
	}




	
    
    public double obtenerStockFiatUsuario(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";
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
    
    public boolean modificarCantidadFiatUsuario(String nomenclatura, double nuevaCantidad) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?";
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
    
    public boolean Existe(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT NOMENCLATURA FROM ACTIVO_FIAT WHERE TRIM(UPPER(NOMENCLATURA)) = TRIM(UPPER(?))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia en la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    
    public FiduciariaUsuario obtenerFiduciariaPorNomenclatura(String nomenclatura) throws SQLException {
    	FiduciariaUsuario fiduciariaUsuario = null; // Inicializamos como null, si no se encuentra el registro, se devuelve null.

	    Connection connection = MyConnection.getCon();

	    // Consulta SQL para obtener solo los datos de la moneda con la nomenclatura dada
	    String sql = "SELECT * FROM ACTIVO_FIAT WHERE NOMENCLATURA = ?";

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
	                fiduciariaUsuario = new FiduciariaUsuario(cantidad, nomenclatura, idUsuario, id, idMoneda);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al obtener la fiduciaria por nomenclatura: " + e.getMessage());
	    }

	    return fiduciariaUsuario; // Devolvemos el objeto o null si no se encontró
	}



}
