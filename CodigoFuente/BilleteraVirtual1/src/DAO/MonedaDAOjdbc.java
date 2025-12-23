package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Java.MyConnection;
import Modelos.Moneda;

public class MonedaDAOjdbc implements MonedaDAO{
	

	public boolean Existe(String nomenclatura) throws SQLException {
		Connection connection= MyConnection.getCon();
        String sql = "SELECT NOMENCLATURA FROM MONEDA WHERE TRIM(UPPER(NOMENCLATURA)) = TRIM(UPPER(?))";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar la existencia en la base de datos: " + e.getMessage());
            return false;
        }
    }
    
    // Método para guardar la moneda en la base de datos
	public void guardarEnBD(Moneda moneda) throws SQLException {
	    Connection connection = MyConnection.getCon();

	    // Verificar si la moneda ya existe
	    String checkSql = "SELECT STOCK FROM MONEDA WHERE NOMENCLATURA = ?";
	    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
	        checkStmt.setString(1, moneda.getNomenclatura());
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            // La moneda ya existe, actualizar el stock
	            double stockExistente = rs.getDouble("STOCK");
	            double nuevoStock = stockExistente + moneda.getStock(); // Sumar el stock actual al nuevo

	            String updateSql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
	            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
	                updateStmt.setDouble(1, nuevoStock);
	                updateStmt.setString(2, moneda.getNomenclatura());
	                updateStmt.executeUpdate();
	                System.out.println("Stock actualizado para la moneda: " + moneda.getNomenclatura());
	            }
	        } else {
	            // La moneda no existe, realizar la inserción
	            String insertSql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK, NOMBRE_ICONO) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
	                insertStmt.setString(1, moneda.getTipo());
	                insertStmt.setString(2, moneda.getNombre());
	                insertStmt.setString(3, moneda.getNomenclatura());
	                insertStmt.setDouble(4, moneda.getValorDolar());
	                insertStmt.setObject(5, moneda.getVolatilidad()); // Volatilidad puede ser NULL para FIAT
	                insertStmt.setDouble(6, moneda.getStock());
	                insertStmt.setObject(7, moneda.getNombreIcono());
	                insertStmt.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al guardar o actualizar la moneda en la base de datos: " + e.getMessage());
	        throw e; // Relanzar la excepción para manejo en un nivel superior si es necesario
	    }
	}

    
    public List<Moneda> listarMonedas() throws SQLException {
        List<Moneda> listaMonedas = new ArrayList<>();

        Connection connection = MyConnection.getCon();

        // Consulta SQL para obtener todos los datos de la tabla "MONEDA"
        String sql = "SELECT * FROM MONEDA";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Recorre los resultados e inserta en la lista
            while (rs.next()) {
                int id = rs.getInt("ID");
                String tipo = rs.getString("TIPO");
                String nombre = rs.getString("NOMBRE");
                String nomenclatura = rs.getString("NOMENCLATURA");
                double valorDolar = rs.getDouble("VALOR_DOLAR");
                Double volatilidad = rs.getObject("VOLATILIDAD", Double.class); // Permite null
                Double stock = rs.getObject("STOCK", Double.class); // Permite null
                String nombreIcono = rs.getString("NOMBRE_ICONO");

                // Crea un objeto Moneda con los datos obtenidos
                Moneda moneda = new Moneda(id, tipo, nombre, nomenclatura, valorDolar, volatilidad, stock, nombreIcono);

                listaMonedas.add(moneda);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las monedas: " + e.getMessage());
            throw e; // Relanzar excepción para manejarla en otro nivel
        }

        return listaMonedas;
    }
    
    public List<Moneda> listarStock() throws SQLException {
        List<Moneda> listaMonedas = new ArrayList<>();

        Connection connection = MyConnection.getCon();

        // Consulta SQL para obtener todos los datos de la tabla "MONEDA"
        String sql = "SELECT * FROM MONEDA";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Recorre los resultados e inserta en la lista
            while (rs.next()) {
                String tipo = rs.getString("TIPO");
                String nomenclatura = rs.getString("NOMENCLATURA");
                double valorDolar = rs.getDouble("VALOR_DOLAR");
                Double stock = rs.getObject("STOCK", Double.class); // Permite null

                // Crea un objeto Moneda con los datos obtenidos
                Moneda moneda = new Moneda(tipo, nomenclatura, valorDolar,  stock );

                listaMonedas.add(moneda);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las monedas: " + e.getMessage());
            throw e; // Relanzar excepción para manejarla en otro nivel
        }

        return listaMonedas;
    }
    public void generarStockAleatorio() throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sqlSelect = "SELECT NOMENCLATURA, STOCK FROM MONEDA";
        
        try (Statement stmtSelect = connection.createStatement();
             ResultSet rs = stmtSelect.executeQuery(sqlSelect)) {
            
            Random random = new Random();
            
            while (rs.next()) {
                String nomenclatura = rs.getString("NOMENCLATURA");
                double stockActual = rs.getDouble("STOCK");
                int stockAleatorio = random.nextInt(1000) + 1; // Genera un stock aleatorio entre 1 y 1000
                double nuevoStock = stockActual + stockAleatorio; // Suma el stock actual con el aleatorio
                
                // Actualiza el stock en la base de datos
                String sqlUpdate = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
                try (PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate)) {
                    stmtUpdate.setDouble(1, nuevoStock); // Usa el nuevo stock
                    stmtUpdate.setString(2, nomenclatura);
                    
                    int rowsAffected = stmtUpdate.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error al actualizar el stock: " + e.getMessage());
                }
            }
        }
    }

    
    public double obtenerStockMoneda(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT STOCK FROM MONEDA WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("STOCK");
            } else {
                System.out.println("Error: No tienes " + nomenclatura + " en tu billetera.");
                return 0;
            }
        }
    }
    
    public static double obtenerValorDolar(int ID) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT VALOR_DOLAR FROM MONEDA WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("VALOR_DOLAR");
            } else {
                System.out.println("Error: No tienes " + ID + " en tu billetera.");
                return 0;
            }
        }
    }
    
    public static int obtenerIDMoneda(String nomenclatura) throws SQLException {
        Connection connection = MyConnection.getCon();
        String sql = "SELECT ID FROM MONEDA WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                // Lanza una excepción si no se encuentra la moneda
                throw new SQLException("No se encontró la moneda con nomenclatura: " + nomenclatura);
            }
        }
    }
    
    
    public double obtenerPrecioMoneda(String nomenclatura) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT VALOR_DOLAR FROM MONEDA WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("VALOR_DOLAR");
            } else {
                System.out.println("Error: No tienes " + nomenclatura + " en tu billetera.");
                return 0;
            }
        }
    
    
    }
    
    public boolean modificarCantidadMoneda(String nomenclatura, double nuevaCantidad) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "UPDATE MONEDA SET STOCK = ? WHERE NOMENCLATURA = ?";
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

    
    public boolean modificarValorMoneda(String nomenclatura, double nuevoValorDolar) throws SQLException {
        Connection connection = MyConnection.getCon();
        String sql = "UPDATE MONEDA SET VALOR_DOLAR = ? WHERE NOMENCLATURA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoValorDolar);
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
            System.out.println("Error al modificar el valor en la base de datos: " + e.getMessage());
            return false; // Ocurrió un error al intentar modificar
        }
    }

    

}