package MVC.Vistas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import MVC.Controladores.PositionManager;
import MVC.Controladores.UsuarioActivo;
import MVC.Vistas.Componentes.*;

public class CotizacionesVista extends JFrame implements PropertyChangeListener {

    private final PositionManager positionManager;

    private BotonPersonalizado cerrarSesionBoton;
    private BotonPersonalizado comprarBTC;
    private BotonPersonalizado comprarETH;
    private BotonPersonalizado comprarUSDC;
    private BotonPersonalizado comprarUSDT;
    private BotonPersonalizado comprarDOGE;
    private BotonPersonalizado volverBoton;

    private ImagenPersonalizada pfp;

    private ImagenPersonalizada iconoBTC;
    private ImagenPersonalizada iconoETH;
    private ImagenPersonalizada iconoUSDC;
    private ImagenPersonalizada iconoUSDT;
    private ImagenPersonalizada iconoDOGE;

    private LabelPersonalizado btcPrecio;
    private LabelPersonalizado ethPrecio;
    private LabelPersonalizado usdcPrecio;
    private LabelPersonalizado usdtPrecio;
    private LabelPersonalizado dogePrecio;
    private LabelPersonalizado nombreLabel;

    public CotizacionesVista(PositionManager positionManager) {
        // DEFINIR EL FRAME
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);
        
        setTitle("GRUPO 31 - Cotizaciones ");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // INSTANCIAR LOS ELEMENTOS DEL FRAME
        nombreLabel = new LabelPersonalizado("$NOMBRE");
        
        LabelPersonalizado criptoLabel = new LabelPersonalizado("Cripto");
        LabelPersonalizado precioCompraLabel = new LabelPersonalizado("Precio actual");

        LabelPersonalizado btcLabel = new LabelPersonalizado("BTC : $ USD ");
        LabelPersonalizado ethLabel = new LabelPersonalizado("ETH : $ USD ");
        LabelPersonalizado usdcLabel = new LabelPersonalizado("USDC : $ USD");
        LabelPersonalizado usdtLabel = new LabelPersonalizado("USDT : $ USD");
        LabelPersonalizado dogeLabel = new LabelPersonalizado("DOGE : $ USD");
        
         btcPrecio = new LabelPersonalizado("0");
         ethPrecio = new LabelPersonalizado("0");
         usdcPrecio = new LabelPersonalizado("0");
         usdtPrecio = new LabelPersonalizado("0");
         dogePrecio = new LabelPersonalizado("0");

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
        JPanel centro = new JPanel(new GridBagLayout()); 
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

        // Cargar iconos de las criptomonedas 
        Icon iconoBTCIcon = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Monedas/BTC.png")); 
        iconoBTC = new ImagenPersonalizada(); 
        iconoBTC.setPreferredSize(new Dimension(50, 50)); 
        iconoBTC.setImagen(iconoBTCIcon);

        Icon iconoETHIcon = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Monedas/ETH.png")); 
        iconoETH = new ImagenPersonalizada(); 
        iconoETH.setPreferredSize(new Dimension(50, 50)); 
        iconoETH.setImagen(iconoETHIcon); 

        Icon iconoUSDCIcon = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Monedas/USDC.png"));  
        iconoUSDC = new ImagenPersonalizada(); 
        iconoUSDC.setPreferredSize(new Dimension(50, 50)); 
        iconoUSDC.setImagen(iconoUSDCIcon); 

        Icon iconoUSDTIcon = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Monedas/USDT.png"));  
        iconoUSDT = new ImagenPersonalizada(); 
        iconoUSDT.setPreferredSize(new Dimension(50, 50)); 
        iconoUSDT.setImagen(iconoUSDTIcon); 

        Icon iconoDOGEIcon = new ImageIcon(getClass().getClassLoader().getResource("MVC/Imagenes/Monedas/DOGE.png")); ;
        iconoDOGE = new ImagenPersonalizada(); 
        iconoDOGE.setPreferredSize(new Dimension(50, 50));
        iconoDOGE.setImagen(iconoDOGEIcon);

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

        // CONFIGURAR PANEL CENTRO 

        comprarBTC = new BotonPersonalizado("Comprar"); 

        comprarBTC.setFocusPainted(false);
        comprarBTC.setBackground(new Color(46, 204, 113));  
        comprarBTC.setColor(new Color(46, 204, 113));
        comprarBTC.setColorEncima(new Color(88, 214, 141));
        comprarBTC.setColorBorde(new Color(35, 155, 86));
        comprarBTC.setColorClick(new Color(35, 155, 86));

        comprarETH = new BotonPersonalizado("Comprar"); 

        comprarETH.setFocusPainted(false);
        comprarETH.setBackground(new Color(46, 204, 113));  
        comprarETH.setColor(new Color(46, 204, 113));
        comprarETH.setColorEncima(new Color(88, 214, 141));
        comprarETH.setColorBorde(new Color(35, 155, 86));
        comprarETH.setColorClick(new Color(35, 155, 86));

        comprarUSDC = new BotonPersonalizado("Comprar");

        comprarUSDC.setFocusPainted(false);
        comprarUSDC.setBackground(new Color(46, 204, 113));  
        comprarUSDC.setColor(new Color(46, 204, 113));
        comprarUSDC.setColorEncima(new Color(88, 214, 141));
        comprarUSDC.setColorBorde(new Color(35, 155, 86));
        comprarUSDC.setColorClick(new Color(35, 155, 86));

        comprarUSDT = new BotonPersonalizado("Comprar"); 

        comprarUSDT.setFocusPainted(false);
        comprarUSDT.setBackground(new Color(46, 204, 113));  
        comprarUSDT.setColor(new Color(46, 204, 113));
        comprarUSDT.setColorEncima(new Color(88, 214, 141));
        comprarUSDT.setColorBorde(new Color(35, 155, 86));
        comprarUSDT.setColorClick(new Color(35, 155, 86));

        comprarDOGE = new BotonPersonalizado("Comprar"); 

        comprarDOGE.setFocusPainted(false);
        comprarDOGE.setBackground(new Color(46, 204, 113)); 
        comprarDOGE.setColor(new Color(46, 204, 113));
        comprarDOGE.setColorEncima(new Color(88, 214, 141));
        comprarDOGE.setColorBorde(new Color(35, 155, 86));
        comprarDOGE.setColorClick(new Color(35, 155, 86));

        // CONFIGURAR PANEL CENTRO 
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        // Configuración para BTC
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; 
        centro.add(iconoBTC, gbc);

        gbc.gridx = 1; gbc.weightx = 1; // Expandir el label del nombre
        centro.add(btcLabel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; // Más espacio para el precio
        centro.add(btcPrecio, gbc);

        gbc.gridx = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST; // Botón alineado a la derecha
        centro.add(comprarBTC, gbc);

        // Configuración para ETH
        gbc.gridy = 1; gbc.gridx = 0; gbc.weightx = 0;
        centro.add(iconoETH, gbc);

        gbc.gridx = 1; gbc.weightx = 1; 
        centro.add(ethLabel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; 
        centro.add(ethPrecio, gbc);

        gbc.gridx = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST; 
        centro.add(comprarETH, gbc);

        // Configuración para USDC
        gbc.gridy = 2; gbc.gridx = 0; gbc.weightx = 0;
        centro.add(iconoUSDC, gbc);

        gbc.gridx = 1; gbc.weightx = 1; 
        centro.add(usdcLabel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; 
        centro.add(usdcPrecio, gbc);

        gbc.gridx = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST; 
        centro.add(comprarUSDC, gbc);

        // Configuración para USDT
        gbc.gridy = 3; gbc.gridx = 0; gbc.weightx = 0;
        centro.add(iconoUSDT, gbc);

        gbc.gridx = 1; gbc.weightx = 1; 
        centro.add(usdtLabel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; 
        centro.add(usdtPrecio, gbc);

        gbc.gridx = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST; 
        centro.add(comprarUSDT, gbc);

        // Configuración para DOGE
        gbc.gridy = 4; gbc.gridx = 0; gbc.weightx = 0;
        centro.add(iconoDOGE, gbc);

        gbc.gridx = 1; gbc.weightx = 1; 
        centro.add(dogeLabel, gbc);

        gbc.gridx = 2; gbc.weightx = 1; 
        centro.add(dogePrecio, gbc);

        gbc.gridx = 3; gbc.weightx = 0; gbc.anchor = GridBagConstraints.EAST; 
        centro.add(comprarDOGE, gbc);

        // CONFIGURAR PANEL SUR
        sur.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; 
        gbc.weighty = 0;
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

    public void actualizarPrecios(String btc, String eth, String usdc, String usdt, String doge) {
        btcPrecio.setText(btc);
        ethPrecio.setText(eth);
        usdcPrecio.setText(usdc);
        usdtPrecio.setText(usdt);
        dogePrecio.setText(doge);
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
    
    public BotonPersonalizado getComprarBTC() {
        return comprarBTC;
    }

    public void setComprarBTC(BotonPersonalizado comprarBTC) {
        this.comprarBTC = comprarBTC;
    }

    public BotonPersonalizado getComprarETH() {
        return comprarETH;
    }

    public void setComprarETH(BotonPersonalizado comprarETH) {
        this.comprarETH = comprarETH;
    }

    public BotonPersonalizado getComprarUSDC() {
        return comprarUSDC;
    }

    public void setComprarUSDC(BotonPersonalizado comprarUSDC) {
        this.comprarUSDC = comprarUSDC;
    }

    public BotonPersonalizado getComprarUSDT() {
        return comprarUSDT;
    }

    public void setComprarUSDT(BotonPersonalizado comprarUSDT) {
        this.comprarUSDT = comprarUSDT;
    }

    public BotonPersonalizado getComprarDOGE() {
        return comprarDOGE;
    }

    public void setComprarDOGE(BotonPersonalizado comprarDOGE) {
        this.comprarDOGE = comprarDOGE;
    }
    
    public LabelPersonalizado getBtcPrecio() {
        return btcPrecio;
    }

    public void setBtcPrecio(LabelPersonalizado btcPrecio) {
        this.btcPrecio = btcPrecio;
    }

    public LabelPersonalizado getEthPrecio() {
        return ethPrecio;
    }

    public void setEthPrecio(LabelPersonalizado ethPrecio) {
        this.ethPrecio = ethPrecio;
    }

    public LabelPersonalizado getUsdcPrecio() {
        return usdcPrecio;
    }

    public void setUsdcPrecio(LabelPersonalizado usdcPrecio) {
        this.usdcPrecio = usdcPrecio;
    }

    public LabelPersonalizado getUsdtPrecio() {
        return usdtPrecio;
    }

    public void setUsdtPrecio(LabelPersonalizado usdtPrecio) {
        this.usdtPrecio = usdtPrecio;
    }

    public LabelPersonalizado getDogePrecio() {
        return dogePrecio;
    }

    public void setDogePrecio(LabelPersonalizado dogePrecio) {
        this.dogePrecio = dogePrecio;
    }

    public LabelPersonalizado getNombreLabel() {
        return nombreLabel;
    }

    public void setNombreLabel(LabelPersonalizado nombreLabel) {
        this.nombreLabel = nombreLabel;
    }
}