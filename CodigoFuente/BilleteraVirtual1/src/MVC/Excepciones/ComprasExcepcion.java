package MVC.Excepciones;

public class ComprasExcepcion extends Exception {
    // Constructor que acepta un mensaje de error
    public ComprasExcepcion(String message) {
        super(message);
    }
}