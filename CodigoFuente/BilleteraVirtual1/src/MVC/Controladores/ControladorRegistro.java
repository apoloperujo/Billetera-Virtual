package MVC.Controladores;

import MVC.Excepciones.RegistroExcepcion;
import MVC.Vistas.*;
import MVC.Vistas.Componentes.DialogoPersonalizado;
import Modelos.Persona;
import Modelos.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import DAO.UsuarioDAOjdbc;

public class ControladorRegistro {
    private final PositionManager positionManager;

        private LoginVista LoginVista;
        private RegistroVista RegistroVista;
        public ControladorRegistro(LoginVista LoginVista, RegistroVista RegistroVista, PositionManager positionManager) {
            this.positionManager = positionManager;
            this.LoginVista = LoginVista;
            this.RegistroVista = RegistroVista;
            
            // Acción del botón de Login
            this.RegistroVista.getCancelarBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showLogin();
                }
            });
    
            //   Acción del botón de registrarse
            this.RegistroVista.getRegistroBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRegistroAction();
                }
            });  
        }
        private void showLogin() {
            RegistroVista.setVisible(false);

            RegistroVista.getNombresCampo().resetear();
            RegistroVista.getApellidosCampo().resetear();
            RegistroVista.getEmailCampo().resetear();
            RegistroVista.getContraCampo().resetear();
            RegistroVista.getSwitch().resetear();
            
            LoginVista.setVisible(true);
        }

        private void handleRegistroAction() {
            try {
                String nombres = RegistroVista.getNombresCampo().getText();
                if (nombres.equals("") || nombres.equals(RegistroVista.getNombresCampo().getTextoBase())) {
                    this.RegistroVista.getNombresCampo().campoRed();
                    throw new RegistroExcepcion("El campo Nombres está vacío.");
                }

                String apellidos = RegistroVista.getApellidosCampo().getText();
                if (apellidos.equals("") || apellidos.equals(RegistroVista.getApellidosCampo().getTextoBase())) {
                    this.RegistroVista.getApellidosCampo().campoRed();
                    throw new RegistroExcepcion("El campo Apellidos está vacío.");
                }

                String email = RegistroVista.getEmailCampo().getText();
                if (email.equals("") || email.equals(RegistroVista.getEmailCampo().getTextoBase())) {
                    this.RegistroVista.getEmailCampo().campoRed();
                    throw new RegistroExcepcion("El campo Email está vacío.");
                }

                String password = new String(RegistroVista.getContraCampo().getPassword());
                if (password.equals("") || password.equals("Ingrese contraseña")) {
                    this.RegistroVista.getContraCampo().campoRed();
                    throw new RegistroExcepcion("El campo Contraseña está vacío.");
                }

                boolean terminos = RegistroVista.getSwitch().isSeleccionado();
                if (!terminos) {
                    throw new RegistroExcepcion("Debe aceptar los términos y condiciones.");
                }

                // Si todos los campos son válidos, comprobar si el correo ya está registrado
                boolean existeMail = false;
                try {
                    List<Usuario> usuarios = UsuarioDAOjdbc.listarUsuarios();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getEmail().equals(email)) {
                            existeMail = true;
                            break;
                        }
                    }

                    if (existeMail) {
                        throw new RegistroExcepcion("El correo ya está registrado.");
                    } else {
                        // Guardar el usuario en la base de datos
                        UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
                        Usuario user = new Usuario(new Persona(nombres, apellidos), email, password, true);
                        usuarioDAO.guardarEnBD(user);

                        DialogoPersonalizado.mostrarDialogo("Usuario registrado exitosamente.", "INFO");
                        RegistroVista.setVisible(false);
                        LoginVista.setVisible(true);
                    }
                } catch (SQLException ex) {
                    throw new RegistroExcepcion("Ocurrió un error al registrar el usuario. Intente nuevamente.");
                }
            } catch (RegistroExcepcion re) {
                DialogoPersonalizado.mostrarDialogo(re.getMessage(), "ERROR");
            } catch (Exception ex) {
                DialogoPersonalizado.mostrarDialogo("Ocurrió un error inesperado. Intente nuevamente.", "ERROR");
            }
        }

}