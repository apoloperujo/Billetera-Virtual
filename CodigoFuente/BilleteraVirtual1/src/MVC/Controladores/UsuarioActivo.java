package MVC.Controladores;

import Modelos.Persona;

public class UsuarioActivo {
    private int id;
    private int idPersona;
    private String email;
    private String password;
    private boolean aceptaTerminos;
    private Persona persona;

    // Constructor completo
    public UsuarioActivo(int id, int idPersona, String email, String password, boolean aceptaTerminos, Persona persona) {
    	this.persona=persona;
        this.id = id;
        this.idPersona = idPersona;
        this.email = email;
        this.password = password;
        this.aceptaTerminos = aceptaTerminos;
    }

    public UsuarioActivo() {
		// TODO Auto-generated constructor stub
	}

	// Getters y Setters
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public Persona getPersona() {
        return persona;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    // Método toString
    @Override
    public String toString() {
        return "UsuarioActivo{" +
                "id=" + id +
                ", idPersona=" + idPersona +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", aceptaTerminos=" + aceptaTerminos +
                '}';
    }
}