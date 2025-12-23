package MVC.Vistas.Componentes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.*;

public class CampoTextoPersonalizado extends JTextField {
    private String textoBase;

    public String getTextoBase() {
        return textoBase;
    }
    public void setTextoBase(String textoBase) {
        this.textoBase = textoBase;
    }
    
    public CampoTextoPersonalizado(int columns, String textoBase) {
        super(columns);
        this.textoBase=textoBase;
        setText(this.textoBase);
        setFont(new Font("Segoe UI Variable Display", Font.PLAIN, 14));
        setBackground(new Color(50, 50, 50));
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
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
                if (getText().isEmpty()) {
                    setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
                } else {
                    setBorder(BorderFactory.createLineBorder(new Color(22, 153, 222), 2));
                }
            }
        });
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(textoBase)) {
                    setText(""); // Limpia el placeholder
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(textoBase); // Restaura el placeholder
                    setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
                }
            }
        });
    }
    public void resetear(){
        setText(textoBase); 
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
    }
    public void campoRed () {
        setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
    }
}