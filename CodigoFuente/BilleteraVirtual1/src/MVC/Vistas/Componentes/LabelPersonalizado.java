package MVC.Vistas.Componentes;

import javax.swing.*;
import java.awt.*;

public class LabelPersonalizado extends JLabel {

    public LabelPersonalizado(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 18));
        setForeground(Color.WHITE);
    }
}