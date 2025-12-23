package DAO;

import Modelos.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Java.MyConnection;

public class PersonaDAOjdbc implements PersonaDAO {

    public void guardarEnBD(Persona persona) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS ) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellido());
            pstmt.executeUpdate();
            System.out.println("Persona guardada en la base de datos: " + persona.getNombre() + " " + persona.getApellido());
        }
    }
    
    public static int obtenerIDPersona (String nombre) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT ID FROM PERSONA WHERE NOMBRES = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                System.out.println("Error: No hay este " + nombre + " en la app.");
                return 0;
            }
        }
    }

    public static String obtenernombrePersona (int ID) throws SQLException {
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT NOMBRES FROM PERSONA WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("NOMBRES");
            } else {
                System.out.println("Error: No hay este " + ID + " en la app.");
                return null;
            }
        }
    }
    
    
}
