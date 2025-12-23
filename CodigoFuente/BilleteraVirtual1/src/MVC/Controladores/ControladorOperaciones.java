package MVC.Controladores;

import MVC.Vistas.InicioVista;
import MVC.Vistas.LoginVista;
import MVC.Vistas.OperacionesVista;
import MVC.Vistas.Componentes.DialogoPersonalizado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorOperaciones {
    private final PositionManager positionManager;

    private UsuarioActivo user;
    private InicioVista InicioVista;
    private LoginVista LoginVista;
    private OperacionesVista OperacionesVista;
    public ControladorOperaciones(LoginVista LoginVista,
        InicioVista InicioVista,OperacionesVista OperacionesVista,PositionManager positionManager) {
            this.positionManager = positionManager;
            this.LoginVista = LoginVista;
            this.InicioVista = InicioVista;
            this.OperacionesVista = OperacionesVista;

            this.OperacionesVista.getCerrarSesionBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCerrarSesionAction();
                }
            });
            this.OperacionesVista.getVolverBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleVolver();
                }
            });
            }
            private void handleCerrarSesionAction() {
            
                OperacionesVista.setVisible(false);
                LoginVista.getEmailCampo().resetear();
                LoginVista.getContraCampo().resetear();
                DialogoPersonalizado.mostrarDialogo("Sesión cerrada con éxito.", "Te vamos a extrañar :(");  
                LoginVista.setVisible(true);
    
            }
            private void handleVolver(){
                
                OperacionesVista.setVisible(false);
    
                InicioVista.setVisible(true);
            }
            
            public UsuarioActivo getUser() {
                return user;
            }

            public void setUser(UsuarioActivo user) {
                this.user = user;
            }
}
