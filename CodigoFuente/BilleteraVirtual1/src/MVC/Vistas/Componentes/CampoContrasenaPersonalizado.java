package MVC.Vistas.Componentes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.*;

public class CampoContrasenaPersonalizado extends JPasswordField {

    public CampoContrasenaPersonalizado(int columns) {
        super(columns);
        setFont(new Font("Segoe UI Variable Display", Font.PLAIN, 14));
        setBackground(new Color(50, 50, 50)); // Fondo oscuro
        setForeground(Color.WHITE); // Texto blanco
        setCaretColor(Color.WHITE); // Cursor blanco
        setEchoChar('\u2022'); // Caracter para ocultar la contraseña (puntos)
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 225), 2)); // Borde azul
        setText("Ingrese contraseña"); // Restaura el placeholder
        setEchoChar((char) 0); // Desactiva el eco
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cambiarColor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cambiarColor();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método generalmente no es necesario para JTextField
            }

            // Método para cambiar el color del JTextField
            private void cambiarColor() {
                if (new String(getPassword()).isEmpty()) {
                    setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
                } else {
                    setBorder(BorderFactory.createLineBorder(new Color(22, 153, 222), 2));
                }
            }
        });
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(getPassword()).equals("Ingrese contraseña")) {
                    setText(""); // Limpia el placeholder
                    setEchoChar('•'); // Activa el eco para ocultar el texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(getPassword()).isEmpty()) {
                    setText("Ingrese contraseña"); // Restaura el placeholder
                    setEchoChar((char) 0); // Desactiva el eco
                    setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
                }
            }
        });
        
    }
    public void resetear(){
        setText("Ingrese contraseña"); // Restaura el placeholder
        setEchoChar((char) 0); // Desactiva el eco
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
    }
    public void campoRed () {
        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
    }
}