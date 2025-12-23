package MVC.Vistas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import MVC.Controladores.PositionManager;
import MVC.Controladores.UsuarioActivo;
import MVC.Vistas.Componentes.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class OperacionesVista extends JFrame implements PropertyChangeListener {
    private final PositionManager positionManager;

    private BotonPersonalizado cerrarSesionBoton;
    private BotonPersonalizado volverBoton;

    private ImagenPersonalizada pfp;
    
    private UsuarioActivo user;
    
    private AreaTextoPersonalizada areaTexto;
    private LabelPersonalizado nombreLabel;

    public OperacionesVista(PositionManager positionManager) {
        // DEFINIR EL FRAME
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);

        setTitle("GRUPO 31 - Mis operaciones");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nombreLabel = new LabelPersonalizado("");
        LabelPersonalizado infoLabel = new LabelPersonalizado("Transacciones realizadas:");

        cerrarSesionBoton = new BotonPersonalizado("Cerrar sesión");
        cerrarSesionBoton.setFocusPainted(false);
        cerrarSesionBoton.setBackground(new Color(224, 54, 37));
        cerrarSesionBoton.setColor(new Color(224, 54, 37));
        cerrarSesionBoton.setColorEncima(new Color(232, 81, 62));
        cerrarSesionBoton.setColorBorde(new Color(174, 43, 30));
        cerrarSesionBoton.setColorClick(new Color(174, 43, 30));

        volverBoton = new BotonPersonalizado("Volver");
        

        // DEFINIR COMPONENTES DEL FRAME
        JPanel norte = new JPanel();
        JPanel oeste = new JPanel();
        JPanel este = new JPanel();
        JPanel centro = new JPanel(new GridBagLayout()); // Cambié el layout a GridBagLayout
        JPanel sur = new JPanel(new GridBagLayout());

        // CONFIGURAR COMPONENTES DEL FRAME
        norte.setBackground(new Color(30, 30, 30));
        sur.setBackground(new Color(30, 30, 30));
        este.setBackground(new Color(30, 30, 30));
        oeste.setBackground(new Color(30, 30, 30));
        centro.setBackground(new Color(30, 30, 30));

        norte.setPreferredSize(new Dimension(480, 100));
        oeste.setPreferredSize(new Dimension(0, 496));
        centro.setPreferredSize(new Dimension(384, 496));
        este.setPreferredSize(new Dimension(0, 496));
        sur.setPreferredSize(new Dimension(480, 96));

        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar panel superior
        norte.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
        norte.setLayout(new BorderLayout());

        // Cargar icono
        Icon iconoPFP = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Iconos/PFP.png"));
        pfp = new ImagenPersonalizada();
        pfp.setPreferredSize(new Dimension(70, 70));
        pfp.setImagen(iconoPFP);

        // Configurar derecha
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(30, 30, 30));
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cerrarSesionBoton.setAlignmentX(Component.LEFT_ALIGNMENT);
        nombreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        cerrarSesionBoton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelDerecho.add(nombreLabel);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(cerrarSesionBoton);

        // Añadir la imagen al panel norte y alinearla a la derecha
        JPanel imagenPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        imagenPanel.add(pfp);
        imagenPanel.setBackground(new Color(30, 30, 30));
        imagenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));

        norte.add(imagenPanel, BorderLayout.CENTER);
        norte.add(panelDerecho, BorderLayout.EAST);

        // Crear subpanel SUPERIOR 
        JPanel subPanelSuperior = new JPanel();
        subPanelSuperior.setLayout(new BorderLayout());
        subPanelSuperior.setBackground(new Color(30, 30, 30));
        subPanelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel izquierdo para los elementos iniciales
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.setBackground(new Color(30, 30, 30));
        infoLabel.setFont(new Font("Segoe UI Variable Display", Font.BOLD, 24));
        panelIzquierdo.add(infoLabel);

        // Agregar los paneles al subPanelSuperior
        subPanelSuperior.add(panelIzquierdo, BorderLayout.WEST);

        // Crear subpanel MEDIO
        JPanel subPanelMedio = new JPanel();
        subPanelMedio.setLayout(new BorderLayout());
        subPanelMedio.setBackground(new Color(30, 30, 30));
        subPanelMedio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Añadir cuadro de texto con barra de desplazamiento
        areaTexto = new AreaTextoPersonalizada(10, 30);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        subPanelMedio.add(scrollPane, BorderLayout.CENTER);

        /// Configurar el centro  para distribuir los subpaneles
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.01;
        gbc.fill = GridBagConstraints.BOTH;
        centro.add(subPanelSuperior, gbc); 

        gbc.gridy = 1; 
        gbc.weighty = 0.99;
        centro.add(subPanelMedio, gbc);

        // CONFIGURAR PANEL SUR
        sur.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // Asegurarse de que no ocupe todo el ancho
        gbc.weighty = 0; // Asegurarse de que no ocupe todo el alto
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        volverBoton.setPreferredSize(new Dimension(160, 50)); // Ajustar tamaño del botón
        sur.add(volverBoton, gbc);

        // Añadir los paneles al frame
        add(norte, BorderLayout.NORTH);
        add(sur, BorderLayout.SOUTH);
        add(este, BorderLayout.EAST);
        add(oeste, BorderLayout.WEST);
        add(centro, BorderLayout.CENTER);

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                getContentPane().requestFocusInWindow();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                Component clickedComponent = me.getComponent();
                if (!(clickedComponent instanceof JTextField) && !(clickedComponent instanceof JPasswordField)
                        && !(clickedComponent instanceof JButton) && !(clickedComponent instanceof JTable)) {
                    requestFocusInWindow();
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                positionManager.setPosition(getLocation());
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("position".equals(evt.getPropertyName())) {
            setLocation((Point) evt.getNewValue());
        }
    }

    public void updatePosition() {
        positionManager.setPosition(getLocation());
    }
    public BotonPersonalizado getCerrarSesionBoton() {
        return cerrarSesionBoton;
    }

    public void setCerrarSesionBoton(BotonPersonalizado cerrarSesionBoton) {
        this.cerrarSesionBoton = cerrarSesionBoton;
    }

    public BotonPersonalizado getVolverBoton() {
        return volverBoton;
    }

    public void setVolverBoton(BotonPersonalizado volverBoton) {
        this.volverBoton = volverBoton;
    }
    
    public void setAreaTextoPersonalizada(String areaTexto) {
    	this.areaTexto.setText(areaTexto);
    }
    
    public UsuarioActivo getUser() {
        return user;
    }

    public void setUser(UsuarioActivo user) {
        this.user = user;
        this.nombreLabel.setText(user.getPersona().getNombre());
    }
}
