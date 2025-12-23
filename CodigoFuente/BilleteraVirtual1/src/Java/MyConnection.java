package Java;

import java.sql.*;
public class MyConnection {
    private static Connection con = null;
    
    // Bloque estático para inicializar la conexión
    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            creaciónDeTablasEnBD(con);
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
    
    
    

    // Método para obtener la conexión
    public static Connection getCon() {
        return con;
    }
    
    public static void CloseCon() {
    	try {
    		con.close();
    	}
    	catch (SQLException e) {
    		System.out.println("Error de SQL: " + e.getMessage());
    	}
    }
    
    // Constructor privado para evitar instancias
    private MyConnection() {}

    private static void creaciónDeTablasEnBD(Connection connection) throws SQLException {
    	Statement stmt;
    	stmt = connection.createStatement();
    	String sql = "CREATE TABLE  IF NOT EXISTS PERSONA "
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " NOMBRES       VARCHAR(50)    NOT NULL, "
    			+ " APELLIDOS       VARCHAR(50)    NOT NULL "
    			+ ")";
    	stmt.executeUpdate(sql);
    	sql = "CREATE TABLE  IF NOT EXISTS USUARIO " + "(" + " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_PERSONA       INTEGER   NOT NULL, "
    			+ " EMAIL       VARCHAR(50)    NOT NULL, "
    			+ " PASSWORD       VARCHAR(50)    NOT NULL, "
    			+ " ACEPTA_TERMINOS       BOOLEAN    NOT NULL, "
    			+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID)"
    			+ ")";
    	stmt.executeUpdate(sql);
    			
    	sql = "CREATE TABLE  IF NOT EXISTS MONEDA "
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " TIPO       VARCHAR(1)    NOT NULL, "
    			+ " NOMBRE       VARCHAR(50)    NOT NULL, "
    			+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
    			+ " VALOR_DOLAR	REAL     NOT NULL, "
    			+ " VOLATILIDAD	REAL     NULL, "
    			+ " STOCK	REAL     NULL, "
    			+ " NOMBRE_ICONO       VARCHAR(50)    NOT NULL "
    			+ ")";
    	stmt.executeUpdate(sql);
    	sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_CRIPTO"
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " ID_MONEDA INTEGER    NOT NULL, "
    			+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
    			+ " CANTIDAD	REAL    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
    			+ " FOREIGN KEY(ID_MONEDA) REFERENCES MONEDA(ID) "
    			+ ")";
    	stmt.executeUpdate(sql);
    	sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_FIAT"
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " ID_MONEDA INTEGER    NOT NULL, "
    			+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
    			+ " CANTIDAD	REAL    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
    			+ " FOREIGN KEY(ID_MONEDA) REFERENCES MONEDA(ID)"
    			+ ")";
    	stmt.executeUpdate(sql);
    	sql = "CREATE TABLE  IF NOT EXISTS TRANSACCION"
    			+ "("
    			+ " ID     INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " RESUMEN VARCHAR(1000)   NOT NULL, "
    			+ " FECHA_HORA		DATETIME  NOT NULL, "
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID)"
    			+ ")";
    	stmt.executeUpdate(sql);
    	
    	stmt.close();
    }





}
