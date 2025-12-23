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
import MVC.Controladores.UsuarioActivo;
import MVC.Vistas.Componentes.*;

public class InicioVista extends JFrame implements PropertyChangeListener {

    private final PositionManager positionManager;

    private JTable table;
    private DefaultTableModel model;

    private UsuarioActivo user;
    private BotonPersonalizado cerrarSesionBoton;
    private BotonPersonalizado generarDatosBoton;
    private BotonPersonalizado CSVBoton;
    private BotonPersonalizado operacionesBoton;
    private BotonPersonalizado cotizacionesBoton;

    private LabelPersonalizado nombreLabel;
    private ImagenPersonalizada pfp;
    private ImagenPersonalizada balanceFoto;

    private LabelPersonalizado balanceLabel;
    
    public InicioVista(PositionManager positionManager) {
        // DEFINIR EL FRAME
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);
        
        setTitle("GRUPO 31 - Mis activos");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // INSTANCIAR LOS ELEMENTOS DEL FRAME
        nombreLabel= new LabelPersonalizado("");
        LabelPersonalizado infoLabel = new LabelPersonalizado("Balance: USD ");
        balanceLabel = new LabelPersonalizado("0");

        cerrarSesionBoton = new BotonPersonalizado("Cerrar sesión");
        cerrarSesionBoton.setFocusPainted(false);
        cerrarSesionBoton.setBackground(new Color(224, 54, 37));
        cerrarSesionBoton.setColor(new Color(224, 54, 37));
        cerrarSesionBoton.setColorEncima(new Color(232, 81, 62));
        cerrarSesionBoton.setColorBorde(new Color(174, 43, 30));
        cerrarSesionBoton.setColorClick(new Color(174, 43, 30));

        generarDatosBoton = new BotonPersonalizado("Generar datos");
        generarDatosBoton.setFocusPainted(false);
        generarDatosBoton.setBackground(new Color(46, 204, 113 ));
        generarDatosBoton.setColor(new Color(46, 204, 113));
        generarDatosBoton.setColorEncima(new Color(88, 214, 141 ));
        generarDatosBoton.setColorBorde(new Color(35, 155, 86 ));
        generarDatosBoton.setColorClick(new Color(35, 155, 86 ));

        CSVBoton = new BotonPersonalizado("Exportar como CSV");
        operacionesBoton = new BotonPersonalizado("Mis operaciones");
        cotizacionesBoton = new BotonPersonalizado("Cotizaciones");

        // DEFINIR COMPONENTES DEL FRAME
        JPanel norte = new JPanel();
        JPanel oeste = new JPanel();
        JPanel este = new JPanel();
        JPanel centro = new JPanel(new GridBagLayout()); // Cambié el layout a GridBagLayout
        JPanel sur = new JPanel(new GridBagLayout());

        // CONFIGURAR COMPONENTES DEL FRAME
        norte.setBackground(new Color(30, 30, 30));
        sur.setBackground(new Color(30,30, 30));
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

        Icon iconoBalance =new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Iconos/Balance.png"));
        balanceFoto = new ImagenPersonalizada();
        balanceFoto.setPreferredSize(new Dimension(40, 40));
        balanceFoto.setImagen(iconoBalance);

        panelIzquierdo.add(balanceFoto);
        panelIzquierdo.add(infoLabel);
        panelIzquierdo.add(balanceLabel);

        // Agregar los paneles al subPanelSuperior
        subPanelSuperior.add(panelIzquierdo, BorderLayout.WEST);


        JPanel generarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        generarPanel.setBackground(new Color(30, 30, 30)); // Fondo del panel
        generarPanel.setLayout(new BoxLayout(generarPanel, BoxLayout.Y_AXIS)); // Cambiar a BoxLayout
        generarPanel.add(Box.createVerticalStrut(9)); // Añadir espacio superior dinámico
        generarPanel.add(generarDatosBoton);
        subPanelSuperior.add(generarPanel, BorderLayout.EAST);


        // Crear subpanel MEDIO
        JPanel subPanelMedio = new JPanel();
        subPanelMedio.setLayout(new BorderLayout());  
        subPanelMedio.setBackground(new Color(30,30,30));
        subPanelMedio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       // Crear la tabla con modelo vacío inicialmente
       String[] columnNames = {"","Nomenclatura", "Cantidad", "Monto"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return String.class;
                }
                if (columnIndex == 1) {
                    return String.class;
                }
                if (columnIndex == 2) {
                	return Float.class;
                }
                return Float.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };

        // Instanciar la tabla personalizada con el modelo
        table = new TablaPersonalizada(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Habilitar la ordenación de la tabla
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        sorter.setSortable(0, false);
        table.setRowSorter(sorter);
        table.getTableHeader().setReorderingAllowed(false);

        // Establecer la ordenación por defecto en la columna "Activo"
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
            
        // Añadir la tabla al centro del subPanelMedio
        subPanelMedio.add(scrollPane, BorderLayout.CENTER);

        // Configurar y añadir el botón CSVBoton en la parte inferior
        CSVBoton.setFocusPainted(false);
        CSVBoton.setBackground(new Color(46, 204, 113));  // Cambia el color según desees
        CSVBoton.setColor(new Color(46, 204, 113));
        CSVBoton.setColorEncima(new Color(88, 214, 141));
        CSVBoton.setColorBorde(new Color(35, 155, 86));
        CSVBoton.setColorClick(new Color(35, 155, 86));

        subPanelMedio.add(CSVBoton, BorderLayout.SOUTH);
        // Configurar el centro  para distribuir los subpaneles
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

        // Reducir el tamaño de los botones inferiores 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.weightx = 0.5; 
        gbc.weighty = 0; 
        gbc.insets = new Insets(10, 10, 10, 5); 
        // Alinear los botones al centro 
        gbc.anchor = GridBagConstraints.CENTER; 
        // No expandir 
        gbc.fill = GridBagConstraints.NONE;

        operacionesBoton.setPreferredSize(new Dimension(180, 60));

        sur.add(operacionesBoton, gbc); 

        gbc.gridx = 1; 
        gbc.insets = new Insets(10, 5, 10, 10); 

        cotizacionesBoton.setPreferredSize(new Dimension(180, 60)); 
        sur.add(cotizacionesBoton, gbc);
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

    public BotonPersonalizado getGenerarDatosBoton() {
        return generarDatosBoton;
    }

    public void setGenerarDatosBoton(BotonPersonalizado generarDatosBoton) {
        this.generarDatosBoton = generarDatosBoton;
    }

    public BotonPersonalizado getCSVBoton() {
        return CSVBoton;
    }

    public void setCSVBoton(BotonPersonalizado cSVBoton) {
        CSVBoton = cSVBoton;
    }

    public BotonPersonalizado getOperacionesBoton() {
        return operacionesBoton;
    }

    public void setOperacionesBoton(BotonPersonalizado operacionesBoton) {
        this.operacionesBoton = operacionesBoton;
    }

    public BotonPersonalizado getCotizacionesBoton() {
        return cotizacionesBoton;
    }

    public void setCotizacionesBoton(BotonPersonalizado cotizacionesBoton) {
        this.cotizacionesBoton = cotizacionesBoton;
    }
    
    public void setLabelPersonalizado(String nuevoTexto) {
        this.nombreLabel.setText(nuevoTexto);
    }
    
    public UsuarioActivo getUser() {
        return user;
    }

    public void setUser(UsuarioActivo user) {
        this.user = user;
        this.nombreLabel.setText(user.getPersona().getNombre());
    }
    
    public JTable getTable() {
    	return table;
    }
    
    public void setTable(JTable table) {
    	this.table=table;
    }
    
    public DefaultTableModel getModel() {
    	return model;
    }
    
    public LabelPersonalizado getNombreLabel() {
        return nombreLabel;
    }
    
    public LabelPersonalizado getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(LabelPersonalizado balanceLabel) {
        this.balanceLabel = balanceLabel;
    }
}
