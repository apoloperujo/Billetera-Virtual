package MVC.Vistas.Componentes;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
public class ImagenPersonalizada extends JComponent {

    Icon imagen;
    int bordeTamamio = 0;
    Color bordeColor = new Color(60,60,60);

    

    public void paint(Graphics g) {
    if (imagen != null) {
        int width = imagen.getIconWidth();
        int height = imagen.getIconHeight();
        int diameter = Math.min(width, height);

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = mask.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // for image smooth
        g2d.fillOval(0, 0, diameter - 1, diameter - 1);
        g2d.dispose();

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2d = masked.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // for image smooth
        int x = (diameter - width) / 2;
        int y = (diameter - height) / 2;
        g2d.drawImage(toImage(imagen), x, y, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g2d.drawImage(mask, 0, 0, null);
        g2d.dispose();
        Icon icono = new ImageIcon(masked);
        Rectangle tamanio = getAutoSize(icono);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(toImage(icono), tamanio.getLocation().x, tamanio.getLocation().y, tamanio.getSize().width,tamanio.getSize().height,null);
        if (bordeTamamio > 0) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bordeColor);
            g2.setStroke(new BasicStroke(bordeTamamio));
            g2.drawOval(tamanio.x + (bordeTamamio / 2), tamanio.y + (bordeTamamio/2), tamanio.width - bordeTamamio, tamanio.height - bordeTamamio);
        }
        
    }
    else{
        System.out.println("SDASDASDASD\n\n\n\\n" + //
                "\\n" + //
                "\\n" + //
                "\\n" + //
                        "\\n" + //
                        "\\n" + //
                        "\\n" + //
                                "\\n" + //
                                "\\n" + //
                                "");
    }
    super.paint(g);
}
    private Image toImage(Icon icon) {
    return ((ImageIcon) icon).getImage();
}
private Rectangle getAutoSize(Icon image) {
    int w = getWidth();
    int h = getHeight();
    int iw = image.getIconWidth();
    int ih = image.getIconHeight();
    double xScale = (double) w / iw;
    double yScale = (double) h / ih;
    double scale = Math.max(xScale, yScale);
    int width = (int) (scale * iw);
    int height = (int) (scale * ih);
    int x = (w - width) / 2;
    int y = (h - height) / 2;
    return new Rectangle(new Point(x, y), new Dimension(width, height));
}

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }

    public int getBordeTamamio() {
        return bordeTamamio;
    }

    public void setBordeTamamio(int bordeTamamio) {
        this.bordeTamamio = bordeTamamio;
    }

    public Color getBordeColor() {
        return bordeColor;
    }

    public void setBordeColor(Color bordeColor) {
        this.bordeColor = bordeColor;
    }
}
