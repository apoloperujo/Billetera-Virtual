package Modelos;
import java.util.List;
/**
 * La clase Seguridad maneja los parámetros de seguridad de un usuario, incluyendo autenticación de dos pasos,
 * verificación de identidad, y notificaciones.
 * 
 * @author Burgueño-Perujo
 * @version 1.0
 * @since 2024
 */

public class Seguridad {
    private boolean a2p;
    private boolean habilitada;
    private boolean identidad;
    private boolean notificacion;
    private List<Preguntas> preguntasDeSeguridad;
    /**
     * Constructor de la clase Billetera.
     * 
     * @param a2p boolean de la autenticacion de dos pasos.
     * @param habilitada boolean para saber si la cuenta no es sospechosa.
     * @param identidad boolean verificacion de identidad.
     * @param notificacion boolean para enviar notificaciones periodicamente o no. 
     * @param preguntasDeSeguridad lista de preguntas para verificar si el usuario es el correcto.
     */

    // Constructor
    public Seguridad(boolean a2p, boolean habilitada, boolean identidad, boolean notificacion, List<Preguntas> preguntasDeSeguridad) {
        this.a2p = a2p;
        this.habilitada = habilitada;
        this.identidad = identidad;
        this.notificacion = notificacion;
        this.preguntasDeSeguridad = preguntasDeSeguridad;
    }

    // Getters y Setters
    public boolean isA2p() {
        return a2p;
    }

    public void setA2p(boolean a2p) {
        this.a2p = a2p;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public boolean isIdentidad() {
        return identidad;
    }

    public void setIdentidad(boolean identidad) {
        this.identidad = identidad;
    }

    public boolean isNotificacion() {
        return notificacion;
    }

    public void setNotificacion(boolean notificacion) {
        this.notificacion = notificacion;
    }

    public List<Preguntas> getPreguntasDeSeguridad() {
        return preguntasDeSeguridad;
    }

    public void setPreguntasDeSeguridad(List<Preguntas> preguntasDeSeguridad) {
        this.preguntasDeSeguridad = preguntasDeSeguridad;
    }

    // Métodos sin implementación
    
    /**
     * activa la posibilidad de enviar notificaciones.
     * 
     */
    public void activarNotificaciones() {
    }
    
    /**
     * comprueba la autenticacion de 2 factores.
     * 
     */
    public void activarA2PQ() {
    }

    /**
     * comprueba si la cuenta no es sospechosa.
     * 
     */
    public void activarHabilitada() {
    }

    /**
     * comprueba si la identidad es correcta.
     * 
     */
    public void activarIdentidad() {
    }

    /**
     * envia el codigo para la autenticacion de 2 factores.
     * 
     */
    public boolean enviarCod() {
        return false;
    }
    
    /**
     * en caso de estar activa la posibilidad de enviar notificaciones las envia aca.
     * 
     */
    public void enviarNotificacion() {
    }
}