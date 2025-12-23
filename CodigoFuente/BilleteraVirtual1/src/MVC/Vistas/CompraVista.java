package MVC.Vistas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;

import MVC.Controladores.PositionManager;
import MVC.Vistas.Componentes.*;

public class CompraVista extends JFrame implements PropertyChangeListener {

    private final PositionManager positionManager;


    private LabelPersonalizado cantidadComprarLabel;
    private BotonPersonalizado cerrarSesionBoton;
    private BotonPersonalizado convertirBoton;
    private BotonPersonalizado cancelarBoton;
    private BotonPersonalizado confirmarBoton;

    private ImagenPersonalizada pfp;

    private LabelPersonalizado stockDisponible;
    private LabelPersonalizado precioCompra;
    private LabelPersonalizado cantidadCompra;
    private String monedaActual;
    private LabelPersonalizado nombreLabel;

    private CampoTextoPersonalizado cantidadDisponible;

    private ComboPersonalizado comboPersonalizado;
    public CompraVista(PositionManager positionManager) {
    	
        // DEFINIR EL FRAME
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);
        
        setTitle("GRUPO 31 - Comprar activos");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // INSTANCIAR LOS ELEMENTOS DEL FRAME
        nombreLabel = new LabelPersonalizado("$NOMBRE");
        
        LabelPersonalizado stockLabel = new LabelPersonalizado("Stock disponible: ");
        stockLabel.setFont(new Font("Arial", Font.BOLD, 22));
        stockLabel.setHorizontalAlignment(SwingConstants.LEFT);

        stockDisponible = new LabelPersonalizado("1000000");
        stockDisponible.setFont(new Font("Arial", Font.BOLD, 22));
        stockDisponible.setHorizontalAlignment(SwingConstants.LEFT);

        LabelPersonalizado precioLabel = new LabelPersonalizado("Precio de compra: ");
        precioLabel.setFont(new Font("Arial", Font.BOLD, 22));
        precioLabel.setHorizontalAlignment(SwingConstants.LEFT);

        precioCompra = new LabelPersonalizado("0");
        precioCompra.setFont(new Font("Arial", Font.BOLD, 22));
        precioCompra.setHorizontalAlignment(SwingConstants.LEFT);

        LabelPersonalizado cantidadDisponibleLabel = new LabelPersonalizado("Dinero a gastar: ");
        cantidadDisponibleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        cantidadDisponibleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        cantidadCompra = new LabelPersonalizado("0");
        cantidadCompra.setFont(new Font("Arial", Font.BOLD, 22));
        cantidadCompra.setHorizontalAlignment(SwingConstants.LEFT);

        cantidadComprarLabel = new LabelPersonalizado("Cantidad a recibir: ");
        cantidadComprarLabel.setFont(new Font("Arial", Font.BOLD, 22));
        cantidadComprarLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        cantidadDisponible = new CampoTextoPersonalizado(22,"Ej: 10.000");
        cantidadDisponible.setFont(new Font("Arial", Font.BOLD, 18));
        cantidadDisponible.setHorizontalAlignment(SwingConstants.LEFT);

        cerrarSesionBoton = new BotonPersonalizado("Cerrar sesión");
        cerrarSesionBoton.setFocusPainted(false);
        cerrarSesionBoton.setBackground(new Color(224, 54, 37));
        cerrarSesionBoton.setColor(new Color(224, 54, 37));
        cerrarSesionBoton.setColorEncima(new Color(232, 81, 62));
        cerrarSesionBoton.setColorBorde(new Color(174, 43, 30));
        cerrarSesionBoton.setColorClick(new Color(174, 43, 30));
        
        cancelarBoton= new BotonPersonalizado("Cancelar");
        cancelarBoton.setFocusPainted(false);
        cancelarBoton.setBackground(new Color(224, 54, 37));
        cancelarBoton.setColor(new Color(224, 54, 37));
        cancelarBoton.setColorEncima(new Color(232, 81, 62));
        cancelarBoton.setColorBorde(new Color(174, 43, 30));
        cancelarBoton.setColorClick(new Color(174, 43, 30));
        
        convertirBoton= new BotonPersonalizado("Calcular");

        confirmarBoton= new BotonPersonalizado("Comprar");
        confirmarBoton.setFocusPainted(false);
        confirmarBoton.setBackground(new Color(46, 204, 113 ));
        confirmarBoton.setColor(new Color(46, 204, 113));
        confirmarBoton.setColorEncima(new Color(88, 214, 141 ));
        confirmarBoton.setColorBorde(new Color(35, 155, 86 ));
        confirmarBoton.setColorClick(new Color(35, 155, 86 ));

        String[] opciones = {"USD"};
        comboPersonalizado = new ComboPersonalizado(opciones);

        // DEFINIR COMPONENTES DEL FRAME
        JPanel norte = new JPanel();
        JPanel oeste = new JPanel();
        JPanel este = new JPanel();
        JPanel centro = new JPanel(new GridBagLayout()); // Cambié el layout a GridBagLayout
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 10)); // Espaciado entre botones

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
        
       // Actualizar el layout del panel central
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 1: Stock disponible
        gbc.gridx = 0;
        gbc.gridy = 0;
        centro.add(stockLabel, gbc);
        gbc.gridx = 1;
        centro.add(stockDisponible, gbc);

        // Fila 2: Precio de compra
        gbc.gridx = 0;
        gbc.gridy = 1;
        centro.add(precioLabel, gbc);
        gbc.gridx = 1;
        centro.add(precioCompra, gbc);

        // Fila 3: Dinero a usar (solo label)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3; // Ocupa las columnas
        centro.add(cantidadDisponibleLabel, gbc);

        // Fila 4: Campo de texto y combo en la misma fila
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Campo de texto ocupa más espacio
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centro.add(cantidadDisponible, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = 1; // Combo ocupa una columna
        gbc.fill = GridBagConstraints.NONE;
        centro.add(comboPersonalizado, gbc);

        // Fila 5: Cantidad a recibir
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3; // Ocupa tres columnas
        gbc.anchor = GridBagConstraints.CENTER;
        centro.add(convertirBoton, gbc);
        

        // Fila 6: Botón Convertir
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3; // Ocupa tres columnas
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(cantidadComprarLabel, gbc);
        // CONFIGURAR PANEL SUR

       // Configurar botones inferiores
       cancelarBoton.setPreferredSize(new Dimension(150, 60));
       confirmarBoton.setPreferredSize(new Dimension(150, 60));

       sur.add(cancelarBoton);
       sur.add(confirmarBoton);
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

    public BotonPersonalizado getConvertirBoton() {
        return convertirBoton;
    }

    public BotonPersonalizado getCancelarBoton() {
        return cancelarBoton;
    }

    public BotonPersonalizado getConfirmarBoton() {
        return confirmarBoton;
    }
    public CampoTextoPersonalizado getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(CampoTextoPersonalizado cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public String getMonedaActual() {
        return monedaActual;  
    }

    public void setMonedaActual(String monedaActual) {
        this.monedaActual=monedaActual;
    }
    
    public LabelPersonalizado getCantidadComprarLabel() {
    	return this.cantidadComprarLabel;
    }
    
    public LabelPersonalizado getNombreLabel() {
        return nombreLabel;
    }

    public void setNombreLabel(LabelPersonalizado nombreLabel) {
        this.nombreLabel = nombreLabel;
    }
    
    public LabelPersonalizado getStockDisponible() {
        return stockDisponible;
    }

    public LabelPersonalizado getPrecioCompra() {
        return precioCompra;
    }

    public LabelPersonalizado getCantidadCompra() {
        return cantidadCompra;
    }
}