package Modelos;
import java.util.List;

// import Soporte;

/**
 * La clase Sistema maneja la interacción con los usuarios y el soporte del sistema.
 * 
 * @author Burgueño-Perujo
 * @version 1.0
 * @since 2024
 */
public class Sistema {
    // Atributos
   // private Soporte soporte;
    private List<Usuario> usuarios;

    /**
     * Constructor de la clase Sistema.
     * 
     * @param soporte Soporte técnico del sistema.
     * @param usuarios Lista de usuarios registrados.
     */
  //    public Sistema(Soporte soporte, List<Usuario> usuarios) {
  //      this.soporte = soporte;
  //     this.usuarios = usuarios;
  //}

    /**
     * Busca un usuario por su nombre.
     * 
     * @param nombre Nombre del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario buscarUsuario(String nombre) {
        return null;
    }

    /**
     * Consulta el precio de una criptomoneda.
     * 
     * @param criptomoneda Criptomoneda de la que se desea obtener el precio.
     * @return Precio de la criptomoneda.
     */
  //  public double consultarPrecio(Criptomoneda criptomoneda) {
  //      return 0.0;
  //  }

    /**
     * Consulta las tasas de una criptomoneda.
     * 
     * @param criptomoneda Criptomoneda de la cual se desea consultar las tasas.
     * @return Lista de blockchains con las tasas relacionadas.
     */
  //  public List<Blockchain> consultarTasas(Criptomoneda criptomoneda) {
  //      return null;
  //  }

    /**
     * Crea un nuevo usuario en el sistema.
     * 
     * @param nuevoUsuario Usuario que se desea crear.
     */
    public void crearUsuario(Usuario nuevoUsuario) {
    }

    /**
     * Da de alta a un usuario.
     * 
     * @param usuario Usuario a darse de alta.
     */
    public void darseDeAlta(Usuario usuario) {
    }

    /**
     * Envía una alerta a un usuario.
     * 
     * @param usuario Usuario al que se le enviará la alerta.
     */
    public void enviarAlerta(Usuario usuario) {
    }

    /**
     * Inicia sesión de un usuario.
     * 
     * @param usuario Usuario que desea iniciar sesión.
     */
    public void iniciarSesion(Usuario usuario) {
    }

    /**
     * Obtiene el soporte del sistema.
     * 
     * @return Soporte del sistema.
     */
/*    public Soporte obtenerSoporte() {
        return null;
    }
*/
    /**
     * Genera un reporte del saldo de un usuario.
     * 
     * @param nombreUsuario Nombre del usuario del que se desea consultar el saldo.
     * @return Saldo del usuario.
     */
    public double reporteSaldo(String nombreUsuario) {
        return 0.0;
    }

    /**
     * Realiza una revisión periódica de un usuario.
     * 
     * @param usuario Usuario que será revisado.
     */
    public void revisionPeriodica(Usuario usuario) {
    }

    // Getters y Setters
 /*   public Soporte getSoporte() {
        return soporte;
    }

    public void setSoporte(Soporte soporte) {
        this.soporte = soporte;
    }
*/
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}