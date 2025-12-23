package MVC.Vistas.Componentes;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonPersonalizado extends JButton {
    private boolean encima;
    private boolean click;
    private Color color;
    private Color colorEncima;
    private Color colorClick;
    private Color colorBorde;
    private int radio;

    public boolean isEncima() {
        return encima;
    }

    public void setEncima(boolean encima) {
        this.encima = encima;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorEncima() {
        return colorEncima;
    }

    public void setColorEncima(Color colorEncima) {
        this.colorEncima = colorEncima;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public BotonPersonalizado(String texto) {
        super(texto);

        setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(Color.WHITE); // Texto blanco
        setCursor (new Cursor (Cursor. HAND_CURSOR) ) ;
        setColor( new Color(2, 153, 222 ));

        colorEncima = new Color(93, 178, 218);
        colorClick = new Color(6, 95, 137 );
        colorBorde = new Color(6, 95, 137);
        setBounds(0,250,500,500);
        radio = 5;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        addMouseListener (new MouseAdapter() {

            @Override
            public void mouseEntered (MouseEvent me) {
                setBackground(colorEncima);
                encima = true;
            }

            @Override
            public void mouseExited (MouseEvent me) {
                setBackground(color);
                encima = false;
                if(click){
                    setForeground(Color.WHITE);
                }
            }

            @Override
            public void mousePressed (MouseEvent me) {
                setBackground(colorClick);  
                // setForeground(new Color( 208, 211, 212 ));
                setForeground(new Color( 179, 182, 183 ));
                click = true;             
            }

            @Override
            public void mouseReleased (MouseEvent me) {
                if(encima){
                    setBackground(colorEncima);
                }else {
                    setBackground(color);
                }
                setForeground(Color.WHITE);
                click = false;
            }
        });
    }

    @Override
    protected void paintComponent (Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint (RenderingHints. KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor (colorBorde);
        g2.fillRoundRect (0, 0, getWidth(), getHeight (), radio, radio);
        g2.setColor (getBackground() );
        g2.fillRoundRect (0, 0, getWidth(), getHeight () - 4, radio, radio);
        super.paintComponent(grphcs);
    }
}   