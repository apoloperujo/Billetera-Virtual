package MVC.Vistas.Componentes;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TablaPersonalizada extends JTable {

    public TablaPersonalizada(TableModel model) {
        super(model);
        // Configuración general de la tabla
        setFont(new Font("Segoe UI Variable Display", Font.PLAIN, 14));
        setRowHeight(25);
        setBackground(new Color(50, 50, 50));
        setForeground(Color.WHITE);
        setGridColor(new Color(100, 100, 100));
        setSelectionBackground(new Color(100, 100, 100));
        setSelectionForeground(Color.WHITE);

        // Personalizar el encabezado
        JTableHeader header = getTableHeader();
        header.setFont(new Font("Segoe UI Variable Display", Font.BOLD, 14));
        header.setBackground(new Color(2, 153, 222));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false); // Desactivar reordenamiento de columnas
        header.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));

        // Renderizador personalizado para las celdas
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(new Color(100, 100, 100)); // Color de selección
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(60, 60, 60)); // Color normal
                    c.setForeground(Color.WHITE);
                }
                if (c instanceof JLabel) { 
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER); 
                } 
                return c;
            }
        });
    }

    // Método para resetear la selección
    public void resetearSeleccion() {
        clearSelection();
    }

    // Método para cambiar el color de la fila seleccionada
    public void destacarFila(int fila) {
        setRowSelectionInterval(fila, fila);
    }

    // Método para cambiar el color de una celda específica
    public void destacarCelda(int fila, int columna, Color color) {
        getColumnModel().getColumn(columna).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row == fila && column == columna) {
                    c.setBackground(color);
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(50, 50, 50));
                    c.setForeground(Color.WHITE);
                }
                return c;
            }
        });
        repaint();
    }
}
