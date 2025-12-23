package MVC.Vistas;
import MVC.Controladores.PositionManager;
import MVC.Vistas.Componentes.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginVista extends JFrame implements PropertyChangeListener{
    private final PositionManager positionManager;


    private BotonPersonalizado loginBoton;    
    private BotonPersonalizado registroBoton; 
    private CampoTextoPersonalizado emailCampo;
    private CampoContrasenaPersonalizado contraCampo;
    private LabelPersonalizado nombreLabel;
    
    public LoginVista(PositionManager positionManager) {
        // DEFINIR EL FRAME
        this.positionManager = positionManager;
        this.positionManager.addPropertyChangeListener(this);
        setTitle("GRUPO 31 - Inicio de sesion");
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(480, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // INSTANCIAR LOS ELEMENTOS DEL FRAME
        LabelPersonalizado emailLabel = new LabelPersonalizado("E-mail:");
        LabelPersonalizado contraLabel = new LabelPersonalizado("Contraseña:");
        LabelPersonalizado registroLabel = new LabelPersonalizado("¿No tienes cuenta?");
        LabelPersonalizado bienvenido = new LabelPersonalizado("<html><div style='text-align: center;'>" + "¡Bienvenido!" + "</div></html>");
        emailCampo = new CampoTextoPersonalizado(22,"Ingrese E-Mail");
        contraCampo = new CampoContrasenaPersonalizado(22);
        
        loginBoton = new BotonPersonalizado("Iniciar sesión");      
        registroBoton = new BotonPersonalizado("Registrarse"); 

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
        bienvenido.setFont(new Font("Arial", Font.BOLD, 28));
        bienvenido.setHorizontalAlignment(SwingConstants.CENTER);
        bienvenido.setBorder(BorderFactory.createEmptyBorder(70, 20, 20, 20));
        norte.add(bienvenido, gbc);

        // CONSTRUIR TABLA CENTRAL
        // Fila 1: Label Email y Campo Email
        gbc.insets = new Insets(10, 10, 10, 10); // 10px en todas las direcciones
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 0; // Fila 2
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        centro.add(emailLabel, gbc);
        gbc.gridx = 1; // Columna 2
        emailCampo.setPreferredSize(new Dimension(emailCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        centro.add(emailCampo, gbc);

        // Fila 2: Label Contraseña y Campo Contraseña
        gbc.insets = new Insets(50, 10, 70, 10); // 10px en todas las direcciones
        gbc.gridx = 0; // Columna 1
        gbc.gridy = 2; // Fila 3
        gbc.anchor = GridBagConstraints.EAST;
        centro.add(contraLabel, gbc);
        gbc.gridx = 1; // Columna 2
        contraCampo.setPreferredSize(new Dimension(emailCampo.getPreferredSize().width, 32));
        gbc.anchor = GridBagConstraints.WEST;
        centro.add(contraCampo, gbc);

        // Fila 3: Espacio vacío 
        gbc.gridx = 0;
        gbc.gridy = 3; // Fila 5
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        centro.add(espacioVacio, gbc);

        gbc.insets = new Insets(10, 10, 10, 10); // 10px en todas las direcciones
        // Fila 4: Botón Login
        loginBoton.setPreferredSize(new Dimension(loginBoton.getPreferredSize().width, 50)); 
        gbc.gridx = 0;
        gbc.gridy = 4; // Fila 4
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.fill = GridBagConstraints.HORIZONTAL; // Extender horizontalmente
        gbc.weighty = 0;
        centro.add(loginBoton, gbc);

        // CONSTRUIR TABLA SUR

        sur.add(registroLabel);
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

    public BotonPersonalizado getLoginBoton() {
        return loginBoton;
    }

    public void setLoginBoton(BotonPersonalizado loginBoton) {
        this.loginBoton = loginBoton;
    }

    public BotonPersonalizado getRegistroBoton() {
        return registroBoton;
    }

    public void setRegistroBoton(BotonPersonalizado registroBoton) {
        this.registroBoton = registroBoton;
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
    
    public void setLabelPersonalizado(String nuevoTexto) {
        this.nombreLabel.setText(nuevoTexto);
    }
    
}
