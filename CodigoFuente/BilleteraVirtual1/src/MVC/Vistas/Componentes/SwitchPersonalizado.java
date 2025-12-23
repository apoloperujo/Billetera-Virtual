package MVC.Vistas.Componentes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class SwitchPersonalizado extends Component {

    private Timer timer;
    private float ubicacion;
    private boolean seleccionado;
    private boolean mouseEncima;
    private float velocidad = 1.75f;

    public SwitchPersonalizado(){
        setBackground (new Color (2, 153, 222)) ;
        setPreferredSize (new Dimension (50, 25)) ;
        setForeground (Color. WHITE) ;
        setCursor (new Cursor (Cursor. HAND_CURSOR) ) ;
        ubicacion = 2;
        timer = new Timer (0, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                if(seleccionado){
                    int finUbicacion= getWidth()- getHeight() + 2;
                    if(ubicacion<finUbicacion){
                        ubicacion+=velocidad;
                        repaint();
                    } else {
                        timer.stop();
                        ubicacion = finUbicacion;
                        repaint();
                    }
                } else {
                    int finUbicacion= 2;
                    if(ubicacion > finUbicacion){
                        ubicacion-=velocidad;
                        repaint();
                    }else{
                        timer.stop();
                        ubicacion=finUbicacion;
                        repaint();
                    }
                }
            }    
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me){
                mouseEncima= true;
            }
            @Override
            public void mouseExited(MouseEvent me){
                mouseEncima= false;
            }
            @Override
            public void mouseReleased(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    if(mouseEncima){
                        seleccionado = !seleccionado;
                        timer.start();
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics grphcs){
    Graphics2D g2 = (Graphics2D) grphcs;
    g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints. VALUE_ANTIALIAS_ON) ; 
    int width = getWidth();
    int height = getHeight();
    float alpha= obtenerAlpha();
    if(alpha<1){
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(0,0,width,height,25,25);
    }
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g2.setColor (getBackground ());
    g2.fillRoundRect (0, 0, width, height, 25, 25);
    g2.setColor(getForeground());
    g2.setComposite(AlphaComposite.SrcOver);
    g2.fillOval( (int)ubicacion,2,height-4,height-4);
    super.paint(grphcs);
    }

    private float obtenerAlpha(){
        float width = getWidth() - getHeight();
        float alpha = (ubicacion - 2) / width;
        if(alpha<0){
            alpha =0;
        }
        if(alpha>1){
            alpha=1;
        }
        return alpha;
    }
    public void resetear(){
        seleccionado = false;
        timer.start();
    }
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
