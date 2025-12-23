package Modelos;
import java.util.List;

/**
 * La clase Usuario representa a un usuario del sistema con información sobre su billetera, datos y configuración de seguridad.
 * 
 * @author Burgueño-Perujo
 * @version 1.0
 * @since 2024
 */

public class Usuario {
    // Atributos
    private List<Alerta> alertas;
    private Seguridad seguridad;
    private Persona persona;
    private String email;
    private String contraseña;
    private boolean aceptaTerminos;
    private int ID_Persona;
    private int ID;
    
    public Usuario(Persona persona, String email, String contraseña, boolean aceptaTerminos) {
        this.persona = persona;
        this.email = email;
        this.contraseña = contraseña;
        this.aceptaTerminos = aceptaTerminos;
    }

    public Usuario(int ID,int ID_Persona, String email, String contraseña, boolean aceptaTerminos){
    	this.ID=ID;
    	this.ID_Persona=ID_Persona;
        this.email = email;
        this.contraseña = contraseña;
        this.aceptaTerminos = aceptaTerminos;
    }
    
    public Usuario() {
	}

	public Usuario(String email, String password) {
    this.email = email;
    this.contraseña = password;
}

	public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean getAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(boolean aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }
    
    public Integer getID_Persona() {
        return ID_Persona;
    }

    public void setID_Persona(Integer ID_Persona) {
        this.ID_Persona = ID_Persona;
    }
    
    public Integer getID() {
        return ID;
    }
    
    
    
    


    // Métodos

    /**
     * Consulta el saldo total en una fiduciaria específica.
     * 
     * @param fiduciaria La fiduciaria de la cual se desea retornar el saldo.
     * @return El saldo total en la fiduciaria especificada.
     */
    public double consultarSaldoTotal(CriptoUsuario fiduciaria) {
        return 0.0;
    }
    
    /**
     * Consulta el saldo total en la fiduciaria del pais propio.
     * 
     * @return El saldo total en la fiduciaria del pais propio.
     */
    public double consultarSaldoTotal() {
        return 0.0;
    }
    
    /**
     * Consulta el saldo en una criptomoneda específica.
     * 
     * @param criptomoneda La Criptomoneda de la cual se desea consultar el saldo.
     * @return El saldo en la criptomoneda especificada.
     */
    public double consultarSaldo(CriptoUsuario criptomoneda) {
        return 0.0;
    }

    /**
     * Consulta la cantidad de una criptomoneda específica.
     * 
     * @param criptomoneda La criptomoneda de la cual se desea consultar la cantidad.
     * @return La cantidad de la criptomoneda especificada.
     */
    public double consultarCantidad(CriptoUsuario criptomoneda) {
        return 0.0;
    }
    
    /**
     * Configura una alerta para una criptomoneda específica.
     * 
     * @param monto Monto en el que se debe configurar la alerta.
     * @param criptomoneda La criptomoneda para la cual se configura la alerta.
     */
    public void configurarAlerta(double monto, CriptoUsuario criptomoneda) {
    }

    /**
     * Realiza la baja del usuario del sistema.
     */
    public void darseDeBaja() {
    }

    // Getters y Setters
    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public Seguridad getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Seguridad seguridad) {
        this.seguridad = seguridad;
    }
}