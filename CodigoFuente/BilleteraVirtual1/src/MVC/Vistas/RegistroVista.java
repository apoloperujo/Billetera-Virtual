package MVC.Vistas;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import MVC.Controladores.PositionManager;
import MVC.Vistas.Componentes.BotonPersonalizado;
import MVC.Vistas.Componentes.CampoContrasenaPersonalizado;
import MVC.Vistas.Componentes.CampoTextoPersonalizado;
import MVC.Vistas.Componentes.LabelPersonalizado;
import MVC.Vistas.Componentes.SwitchPersonalizado;
import MVC.Excepciones.RegistroExcepcion;
public class RegistroVista extends JFrame implements PropertyChangeListener{
    private final PositionManager positionManager;

    
    private CampoTextoPersonalizado nombresCampo;
    private CampoTextoPersonalizado apellidosCampo;
    private CampoTextoPersonalizado emailCampo;
    private CampoContrasenaPersonalizado contraCampo;
    private SwitchPersonalizado Switch;
    private BotonPersonalizado registroBoton; 
    private BotonPersonalizado cancelarBoton; 

    public RegistroVista(PositionManager positionManager) {
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);
         // DEFINIR EL FRAME
        setTitle("GRUPO 31 - Registrarse");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // INSTANCIAR LOS ELEMENTOS DEL FRAME
        LabelPersonalizado nombresLabel = new LabelPersonalizado("Nombre/s:");
        LabelPersonalizado apellidosLabel = new LabelPersonalizado("Apellido/s:");
        LabelPersonalizado emailLabel = new LabelPersonalizado("E-mail:");
        LabelPersonalizado contraLabel = new LabelPersonalizado("Contraseña:");
        LabelPersonalizado terminosLabel = new LabelPersonalizado("Acepto Terminos y condiciones.");
        LabelPersonalizado registroCartel = new LabelPersonalizado("<html><div style='text-align: center;'>" + "Registrarse:" + "</div></html>");
        
        registroBoton = new BotonPersonalizado("Registrarse");      
        cancelarBoton = new BotonPersonalizado("Cancelar"); 
        Switch = new SwitchPersonalizado();

        nombresCampo = new CampoTextoPersonalizado(22,"Ingrese nombre/s");
        apellidosCampo = new CampoTextoPersonalizado(22,"Ingrese apellido/s");
        emailCampo = new CampoTextoPersonalizado(22,"Ingrese E-Mail");
        contraCampo = new CampoContrasenaPersonalizado(22);

        // DEFINIR COMPONENTES DEL FRAME
        JPanel norte = new JPanel();
        JPanel oeste = new JPanel();
        JPanel este = new JPanel();
        JPanel centro = new JPanel(new GridBagLayout());
        JPanel sur = new JPanel(new GridBagLayout());

        // CONFIGURAR COMPONENTES DEL FRAME

        norte.setBackground(new Color(30, 30, 30));
        sur.setBackground(new Color(30, 30, 30));
        este.setBackground(new Color(30, 30, 30));
        oeste.setBackground(new Color(30, 30, 30));
        centro.setBackground(new Color(30, 30, 30));

        norte.setPreferredSize(new Dimension(480,150));
        oeste.setPreferredSize(new Dimension(24,496));
        centro.setPreferredSize(new Dimension(384,496));
        este.setPreferredSize(new Dimension(24,496));
        sur.setPreferredSize(new Dimension(480,96));

        
        // Configurar panel central con GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar restricciones y agregar componentes
        gbc.insets = new Insets(0, 10, 30, 10); // Espaciado interno
        JPanel espacioVacio = new JPanel();
        espacioVacio.setBackground(new Color(30, 30, 30));
        
        // Norte: Bienvenido
        registroCartel.setFont(new Font("Arial", Font.BOLD, 28));
        registroCartel.setHorizontalAlignment(SwingConstants.CENTER);
        registroCartel.setBorder(BorderFactory.createEmptyBorder(70, 20, 20, 20));
        norte.add(registroCartel, gbc);

        // CONSTRUIR TABLA CENTRAL
        // Fila 1: Label nombres
        gbc.insets = new Insets(30, 10, 10, 10); // 10px en todas las direcciones
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 0; // Fila 1
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        centro.add(nombresLabel, gbc);
        gbc.gridx = 1; // Columna 2
        nombresCampo.setPreferredSize(new Dimension(nombresCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        centro.add(nombresCampo, gbc);

        // Fila 2: Label apellidos
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 2; // Fila 2
        gbc.anchor = GridBagConstraints.EAST;
        centro.add(apellidosLabel, gbc);
        gbc.gridx = 1; // Columna 2
        apellidosCampo.setPreferredSize(new Dimension(apellidosCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(apellidosCampo, gbc);

        // Fila 3: Label email
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 4; // Fila 3
        gbc.anchor = GridBagConstraints.EAST;
        centro.add(emailLabel, gbc);
        gbc.gridx = 1; // Columna 2
        emailCampo.setPreferredSize(new Dimension(emailCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(emailCampo, gbc);

        // Fila 4: Label Contraseña y Campo Contraseña
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 6; // Fila 4
        gbc.anchor = GridBagConstraints.EAST;
        centro.add(contraLabel, gbc);
        gbc.gridx = 1; // Columna 2
        contraCampo.setPreferredSize(new Dimension(contraCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(contraCampo, gbc);

        // Fila 4: terminos
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 8; // Fila 4
        gbc.anchor = GridBagConstraints.EAST;
        centro.add(Switch, gbc);
        gbc.gridx = 1; // Columna 2
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(terminosLabel, gbc);

        cancelarBoton.setFocusPainted(false);
        cancelarBoton.setBackground(new Color(0, 122, 255));
        cancelarBoton.setColor(new Color(224,54,37));
        cancelarBoton.setColorEncima(new Color(232,81,62));
        cancelarBoton.setColorBorde(new Color(174,43,30));
        cancelarBoton.setColorClick(new Color(174,43,30));
        
        sur.add(cancelarBoton);
        espacioVacio.setPreferredSize(new Dimension(50,10));
        sur.add(espacioVacio); // Agregar el panel vacío a la grid
        sur.add(registroBoton);

         // Añadir panel central al frame
         
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
                // Transferir el foco al panel principal si se hace clic en un lugar vacío
                Component clickedComponent = me.getComponent();
                if (!(clickedComponent instanceof JTextField) && !(clickedComponent instanceof JPasswordField) && !(clickedComponent instanceof JButton)) {
                    // Pasar el foco al contenedor principal para desenfocar campos
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
    public SwitchPersonalizado getSwitch() {
        return Switch;
    }
    public void setSwitch(SwitchPersonalizado Switch) {
        this.Switch = Switch;
    }
    public CampoTextoPersonalizado getNombresCampo() {
        return nombresCampo;
    }
    public void setNombresCampo(CampoTextoPersonalizado nombresCampo) {
        this.nombresCampo = nombresCampo;
    }
    public CampoTextoPersonalizado getApellidosCampo() {
        return apellidosCampo;
    }
    public void setApellidosCampo(CampoTextoPersonalizado apellidosCampo) {
        this.apellidosCampo = apellidosCampo;
    }
    public CampoTextoPersonalizado getEmailCampo() {
        return emailCampo;
    }
    public void setEmailCampo(CampoTextoPersonalizado emailCampo) {
        this.emailCampo = emailCampo;
    }
    public CampoContrasenaPersonalizado getContraCampo() {
        return contraCampo;
    }
    public void setContraCampo(CampoContrasenaPersonalizado contraCampo) {
        this.contraCampo = contraCampo;
    }
    public BotonPersonalizado getRegistroBoton() {
        return registroBoton;
    }
    public void setRegistroBoton(BotonPersonalizado registroBoton) {
        this.registroBoton = registroBoton;
    }
    public BotonPersonalizado getCancelarBoton() {
        return cancelarBoton;
    }
    public void setCancelarBoton(BotonPersonalizado cancelarBoton) {
        this.cancelarBoton = cancelarBoton;
    }
}
