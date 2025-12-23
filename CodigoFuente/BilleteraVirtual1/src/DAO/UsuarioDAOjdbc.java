package DAO;

import Modelos.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Java.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO{
	
	// Método para guardar el usuario en la base de datos
    public void guardarEnBD(Usuario usuario) throws SQLException {
    	Connection connection= MyConnection.getCon();
    	PersonaDAOjdbc PersonaDAO= new PersonaDAOjdbc();
    	PersonaDAO.guardarEnBD(usuario.getPersona());
    	
    	usuario.setID_Persona(PersonaDAOjdbc.obtenerIDPersona(usuario.getPersona().getNombre())); //setteo el ID de la usuario con el que tiene la persona
        String sql = "INSERT INTO USUARIO (ID_PERSONA , EMAIL, PASSWORD, ACEPTA_TERMINOS ) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        	
            pstmt.setInt(1, usuario.getID_Persona());
            
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getContraseña());
            pstmt.setBoolean(4, usuario.getAceptaTerminos());
            
            pstmt.executeUpdate();
            System.out.println("Usuario: " + usuario.getPersona().getNombre() + " guardado con exito");
        }
    }

   
    public static int obtenerIDPUsuario (String nombre ) throws SQLException {
    	int ID_persona=PersonaDAOjdbc.obtenerIDPersona(nombre);
    	Connection connection= MyConnection.getCon();
        String sql = "SELECT ID FROM USUARIO WHERE ID_PERSONA = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, ID_persona);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                System.out.println("Error: No existe el usuario asociado a ese ID");
                return 0;
            }
        }
    }
    
    public static List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();

        Connection connection = MyConnection.getCon();

        // Consulta SQL para obtener todos los datos de la tabla "MONEDA"
        String sql = "SELECT * FROM USUARIO";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Recorre los resultados e inserta en la lista
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int ID_Persona = rs.getInt("ID_PERSONA");
                String email = rs.getString("EMAIL");
                String contraseña = rs.getString("PASSWORD");
                boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");

                
                // Crea un objeto Moneda con los datos obtenidos
                Usuario usuario = new Usuario(ID, ID_Persona, email, contraseña, aceptaTerminos);

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las monedas: " + e.getMessage());
            throw e; // Relanzar excepción para manejarla en otro nivel
        }

        return listaUsuarios;
    }
    
}
