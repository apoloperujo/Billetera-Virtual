package MVC.Controladores;
import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PositionManager {
    private Point position;
    private final PropertyChangeSupport support;

    public PositionManager() {
        position = new Point(100, 100); // Posición inicial
        support = new PropertyChangeSupport(this);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point newPosition) {
        Point oldPosition = this.position;
        this.position = newPosition;
        support.firePropertyChange("position", oldPosition, newPosition);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}