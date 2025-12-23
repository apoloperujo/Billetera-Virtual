package MVC.Vistas.Componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoPersonalizado {

    public static void mostrarDialogo(String mensaje, String titulo) {
        // Crear el marco del diálogo
        
        JDialog dialog = new JDialog();
        dialog.setTitle(titulo);
        dialog.setResizable(false);
        dialog.setSize(400, 200);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.setModal(true);
        // Estilo moderno: Color de fondo
        dialog.getContentPane().setBackground(new Color(30, 30, 30));

        // Crear el mensaje con estilo
        LabelPersonalizado labelMensaje = new LabelPersonalizado("<html><div style='text-align: center;'>" + mensaje + "</div></html>");
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelMensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        labelMensaje.setFont(new Font("Arial", Font.PLAIN, 20));
        // Crear botón con estilo
        BotonPersonalizado botonCerrar = new BotonPersonalizado("Cerrar");
        botonCerrar.setFocusPainted(false);
        botonCerrar.setBackground(new Color(0, 122, 255));

        botonCerrar.setColor(new Color(224,54,37));
        botonCerrar.setColorEncima(new Color(232,81,62));
        botonCerrar.setColorBorde(new Color(174,43,30));
        botonCerrar.setColorClick(new Color(174,43,30));
        

        botonCerrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botonCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Acción del botón
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cierra el cuadro de diálogo
            }
        });

        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.add(botonCerrar);

        // Agregar componentes al diálogo
        dialog.add(labelMensaje, BorderLayout.CENTER);
        dialog.add(panelBoton, BorderLayout.SOUTH);

        // Centrar y mostrar el cuadro de diálogo
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

  
}