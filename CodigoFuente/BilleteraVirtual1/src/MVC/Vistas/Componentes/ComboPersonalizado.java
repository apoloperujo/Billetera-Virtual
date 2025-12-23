package MVC.Vistas.Componentes;

import javax.swing.*;
import java.awt.*;

public class ComboPersonalizado extends JComboBox<String> {

    public ComboPersonalizado(String[] items) {
        super(items);
        // Configuración general del combo box
        setFont(new Font("Segoe UI Variable Display", Font.PLAIN, 14));
        setBackground(new Color(50, 50, 50));
        setForeground(Color.WHITE);
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(new Color(100, 100, 100)); // Color de selección
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(60, 60, 60)); // Color normal
                    c.setForeground(Color.WHITE);
                }
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
                }
                return c;
            }
        });

        // Configuración del borde del combo box
        setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
    }

    // Método para añadir un ítem
    public void agregarItem(String item) {
        addItem(item);
    }

    // Método para eliminar un ítem
    public void eliminarItem(String item) {
        removeItem(item);
    }

    // Método para limpiar todos los ítems
    public void limpiarItems() {
        removeAllItems();
    }
}