package MVC.Vistas;

import javax.swing.*;
import java.awt.*;

public class AreaTextoPersonalizada extends JTextArea {

    public AreaTextoPersonalizada(int rows, int columns) {
        super(rows, columns);
        // Configuración general del área de texto
        setFont(new Font("Segoe UI Variable Display", Font.PLAIN, 14));
        setBackground(new Color(50, 50, 50));
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE); // Color del cursor
        setSelectionColor(new Color(100, 100, 100)); // Color de selección
        setSelectedTextColor(Color.WHITE); // Color del texto seleccionado
        setLineWrap(true); // Ajuste de línea
        setWrapStyleWord(true); // Ajuste de palabras

        // Configuración del borde del área de texto
        setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
    }
    
    // Método para establecer el texto
    public void establecerTexto(String texto) {
        setText(texto);
    }
    
    // Método para añadir texto
    public void agregarTexto(String texto) {
        append(texto);
    }

    // Método para limpiar el texto
    public void limpiarTexto() {
        setText("");
    }
}
