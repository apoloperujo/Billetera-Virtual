package MVC.Controladores;


import MVC.Excepciones.LoginExcepcion;
import MVC.Vistas.*;
import MVC.Vistas.Componentes.DialogoPersonalizado;
import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;
import Modelos.Moneda;
import Modelos.Persona;
import Modelos.Usuario;
import DAO.CriptoUsuarioDAOjdbc;
import DAO.DAOFactory;
import DAO.FiduciariaUsuarioDAOjdbc;
import DAO.MonedaDAO;
import DAO.MonedaDAOjdbc;
import DAO.PersonaDAOjdbc;
import DAO.UsuarioDAOjdbc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

    public class ControladorLogin {
        private final PositionManager positionManager;

        private UsuarioActivo user;
        private LoginVista loginVista;
        private RegistroVista registroVista;
        private InicioVista inicioVista;
        private CotizacionesVista cotizacionesVista;
        public ControladorLogin(LoginVista LoginVista, RegistroVista RegistroVista,
        InicioVista InicioVista,PositionManager positionManager, CotizacionesVista cotizacionesVista) {
        	this.cotizacionesVista=cotizacionesVista;
            this.positionManager = positionManager;
            this.loginVista = LoginVista;
            this.registroVista = RegistroVista;
            this.inicioVista = InicioVista;
            // Acción del botón de Login
            this.loginVista.getLoginBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        handleLoginAction();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (LoginExcepcion le) {
                        // Mostrar el mensaje de la excepción personalizada
                        DialogoPersonalizado.mostrarDialogo(le.getMessage(), "ERROR");
                    }
                }
            });
            //   Acción del botón de registrarse
            this.loginVista.getRegistroBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showRegisterView();
                }
            });  
            
        }
    
        private void handleLoginAction() throws SQLException, LoginExcepcion {
                String email = loginVista.getEmailCampo().getText();
                String password = new String(loginVista.getContraCampo().getPassword());

                // Validar si los campos están vacíos
                if (email.equals("") || email.equals(loginVista.getEmailCampo().getTextoBase())) {
                    this.loginVista.getEmailCampo().campoRed();  // Resaltar campo en rojo
                    throw new LoginExcepcion("El campo Email está vacio.");
                }
                if (password.equals("") || password.equals("Ingrese contraseña")) {
                    this.loginVista.getContraCampo().campoRed();  // Resaltar campo en rojo
                    throw new LoginExcepcion("El campo Contraseña está vacio.");
                }

                // Verificar si el usuario existe
                boolean existe = false;
                Usuario userActivo = new Usuario();
                List<Usuario> usuarios = UsuarioDAOjdbc.listarUsuarios();
                for (Usuario usuario : usuarios) {
                    if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password)) {
                        existe = true;
                        userActivo = usuario;
                        break;
                    }
                }

                // Si el usuario existe, cargar vista de inicio
                if (existe) {
                    UsuarioActivo user = new UsuarioActivo();
                    String nombre = PersonaDAOjdbc.obtenernombrePersona(userActivo.getID_Persona());
                    Persona persona = new Persona(nombre, "    ");
                    user.setPersona(persona);
                    user.setEmail(userActivo.getEmail());
                    user.setPassword(userActivo.getContraseña());
                    user.setIdPersona(userActivo.getID_Persona());
                    inicioVista.setUser(user);
                    cargarActivos();
                    loginVista.setVisible(false);
                    inicioVista.setVisible(true);
                } else {
                    throw new LoginExcepcion("E-Mail o contraseña incorrectos.");
                }
            }

    
        private void showRegisterView() {
            
            loginVista.getEmailCampo().resetear();

            loginVista.getContraCampo().resetear();
            loginVista.setVisible(false);

            registroVista.setVisible(true);
        }
        
        private void cargarActivos() throws SQLException {
        	MonedaDAO monedaDAO = DAOFactory.getMonedaDAO();

            // Crear una lista de monedas específicas para insertar en la base de datos
            List<Moneda> monedas = new ArrayList<>();
            monedas.add(new Moneda("FIAT", "Dólar Estadounidense", "USD", 1.0, 0.0, 1000.0, "usd.png"));
            monedas.add(new Moneda("CRIPTO", "Bitcoin", "BTC", 25000.0, 0.1, 100.0, "btc.png"));
            monedas.add(new Moneda("CRIPTO", "Dogecoin", "DOGE", 0.07, 0.15, 5000.0, "doge.png"));
            monedas.add(new Moneda("CRIPTO", "Ethereum", "ETH", 1500.0, 0.05, 200.0, "eth.png"));
            monedas.add(new Moneda("CRIPTO", "USD Coin", "USDC", 1.0, 0.0, 1000.0, "usdc.png"));
            monedas.add(new Moneda("CRIPTO", "Tether", "USDT", 1.0, 0.0, 1200.0, "usdt.png"));

            // Guardar monedas en la base de datos
            for (Moneda moneda : monedas) {
                // Verificar si la moneda ya existe para no duplicar datos
                if (!monedaDAO.Existe(moneda.getNomenclatura())) {
                    monedaDAO.guardarEnBD(moneda);
                }
            }

            // Generar stock aleatorio para las monedas
            monedaDAO.generarStockAleatorio();

            // Cargar activos en la vista
            Moneda dolar = new Moneda("FIAT", "Dólar Estadounidense", "USD", 1.0, 0.0, 1000.0, "usd.png");
        	Moneda btc = new Moneda("CRIPTO", "Bitcoin", "BTC", 50000.0, 1.5, 1000000.0, "btc.png");
        	Moneda eth = new Moneda("CRIPTO", "Ethereum", "ETH", 4000.0, 1.2, 5000000.0, "eth.png");
        	Moneda doge = new Moneda("CRIPTO", "Dogecoin", "DOGE", 0.3, 1.1, 8000000.0, "doge.png");
        	Moneda usdt = new Moneda("CRIPTO", "Tether", "USDT", 1.0, 0.0, 10000000.0, "usdt.png");
        	Moneda usdc = new Moneda("CRIPTO", "USD Coin", "USDC", 1.0, 0.0, 10000000.0, "usdc.png");
        		
        	CriptoUsuario criptoUsuarioBTC = new CriptoUsuario(0, btc);
        	CriptoUsuario criptoUsuarioETH = new CriptoUsuario(0, eth);
        	CriptoUsuario criptoUsuarioDOGE = new CriptoUsuario(0, doge);
        	CriptoUsuario criptoUsuarioUSDT = new CriptoUsuario(0, usdt);
        	CriptoUsuario criptoUsuarioUSDC = new CriptoUsuario(0, usdc);
        	FiduciariaUsuario fiduciario = new FiduciariaUsuario(0, dolar);
            
        	
            fiduciario.setID_Usuario(this.inicioVista.getUser().getIdPersona());
        	criptoUsuarioBTC.setID_Usuario(inicioVista.getUser().getIdPersona());
        	criptoUsuarioETH.setID_Usuario(inicioVista.getUser().getIdPersona());
        	criptoUsuarioDOGE.setID_Usuario(inicioVista.getUser().getIdPersona());
        	criptoUsuarioUSDT.setID_Usuario(inicioVista.getUser().getIdPersona());
        	criptoUsuarioUSDC.setID_Usuario(inicioVista.getUser().getIdPersona());

        	CriptoUsuarioDAOjdbc criptoUsuarioDAO = new CriptoUsuarioDAOjdbc();        	
        	FiduciariaUsuarioDAOjdbc fiduciariaDAO = new FiduciariaUsuarioDAOjdbc();
        	
        	fiduciariaDAO.guardarEnBD(fiduciario);
        	criptoUsuarioDAO.guardarEnBD(criptoUsuarioBTC);
        	criptoUsuarioDAO.guardarEnBD(criptoUsuarioETH);
        	criptoUsuarioDAO.guardarEnBD(criptoUsuarioDOGE);
        	criptoUsuarioDAO.guardarEnBD(criptoUsuarioUSDT);
        	criptoUsuarioDAO.guardarEnBD(criptoUsuarioUSDC);
        	   	
        	ActualizarTabla();
            
        }
        private void ActualizarTabla() throws SQLException {
            // DAO para obtener datos
            FiduciariaUsuarioDAOjdbc fiduDAO = new FiduciariaUsuarioDAOjdbc();
            CriptoUsuarioDAOjdbc criptoDAO = new CriptoUsuarioDAOjdbc();
            MonedaDAO monedaDAO = DAOFactory.getMonedaDAO();

            // Cargar datos desde la base de datos
            List<Moneda> monedas = monedaDAO.listarMonedas();
            List<CriptoUsuario> criptomonedas = criptoDAO.listarActivosCripto();
            List<FiduciariaUsuario> fiduciarias = fiduDAO.listarActivosFiat();

            // Crear un mapa para precios de monedas
            Map<String, Double> preciosMonedas = new HashMap<>();
            for (Moneda moneda : monedas) {
                preciosMonedas.put(moneda.getNomenclatura(), moneda.getValorDolar());
            }

            // Preparar el modelo de tabla
            DefaultTableModel model = (DefaultTableModel) inicioVista.getModel();
            model.setRowCount(0);

            // Calcular el balance de criptomonedas
            double balanceTotal = 0.0;
            for (CriptoUsuario cripto : criptomonedas) {
                double precio = preciosMonedas.getOrDefault(cripto.getNomenclatura(), 0.0);
                double cantidadDolar = cripto.getCantidad() * precio;
                balanceTotal += cantidadDolar;

                // Agregar fila a la tabla
                Object[] row = {"", cripto.getNomenclatura(), cripto.getCantidad(), cantidadDolar};
                model.addRow(row);
            }

            // Calcular el balance de monedas fiduciarias
            if (fiduciarias != null) {
                for (FiduciariaUsuario fiduciaria : fiduciarias) {
                    double cantidadDolar = fiduciaria.getCantidad();
                    balanceTotal += cantidadDolar;

                    // Agregar fila a la tabla
                    Object[] row = {"", fiduciaria.getNomenclatura(), fiduciaria.getCantidad(), cantidadDolar};
                    model.addRow(row);
                }
            }

            // Redondear y mostrar el balance total
            balanceTotal = Math.round(balanceTotal * 100.0) / 100.0;
            inicioVista.getBalanceLabel().setText(String.valueOf(balanceTotal));
        }
        

        
        public UsuarioActivo getUsuarioActivo() {
        	return user;
        }
    
    }

