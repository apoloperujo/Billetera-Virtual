package MVC.Controladores;

import MVC.Vistas.CotizacionesVista;
import MVC.Vistas.InicioVista;
import MVC.Vistas.LoginVista;
import MVC.Vistas.OperacionesVista;
import MVC.Vistas.Componentes.DialogoPersonalizado;
import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;
import Modelos.Moneda;
import Modelos.Transaccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import DAO.CriptoUsuarioDAOjdbc;
import DAO.DAOFactory;
import DAO.FiduciariaUsuarioDAOjdbc;
import DAO.MonedaDAO;
import DAO.MonedaDAOjdbc;
import DAO.TransaccionesDAOjdbc;
import Java.ExportarComoCSV;
public class ControladorInicio {
    private final PositionManager positionManager;

    private UsuarioActivo user;
    private InicioVista InicioVista;
    private LoginVista LoginVista;
    private OperacionesVista OperacionesVista;
    private CotizacionesVista cotizacionesVista;

    public ControladorInicio(LoginVista LoginVista,
        InicioVista InicioVista,OperacionesVista OperacionesVista, PositionManager positionManager , CotizacionesVista cotizacionesVista) {
            this.positionManager = positionManager;
            this.LoginVista = LoginVista;
            this.InicioVista = InicioVista;
            this.OperacionesVista = OperacionesVista;
            this.cotizacionesVista=cotizacionesVista;
            // Acción del botón de Login
            this.InicioVista.getCerrarSesionBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCerrarSesionAction();
                }
            });
            this.InicioVista.getOperacionesBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleMisOperaciones();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            });
            
            this.InicioVista.getCSVBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    	try {
							handleExportarCSV();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

                }
            });

		    this.InicioVista.getCotizacionesBoton().addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            handleMisCotizaciones();
		        }
		    });
            
             
            this.InicioVista.getGenerarDatosBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	try {
						handleGenerarDatos();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            });
    }
        
        private void handleCerrarSesionAction() {
            InicioVista.setVisible(false);
            LoginVista.getEmailCampo().resetear();
            LoginVista.getContraCampo().resetear();
            DialogoPersonalizado.mostrarDialogo("Sesión cerrada con éxito." , " Te vamos a extrañar :(");  
            LoginVista.setVisible(true);

        }
        private void handleMisOperaciones() throws SQLException {
            this.OperacionesVista.setUser(this.InicioVista.getUser());

            // Obtener las transacciones desde el DAO
            TransaccionesDAOjdbc transaccionesDAO = new TransaccionesDAOjdbc();
            List<Transaccion> transacciones = transaccionesDAO.listarTransacciones();

            // Construir el texto con las transacciones
            StringBuilder texto = new StringBuilder();
            for (Transaccion transaccion : transacciones) {
                texto.append(transaccion.toString()).append(System.lineSeparator());
            }

            // Configurar la vista
            OperacionesVista.setAreaTextoPersonalizada(texto.toString());
            InicioVista.setVisible(false);
            OperacionesVista.setVisible(true);
        }

    
        

        
    
    public void handleGenerarDatos() throws SQLException {
    	
         Moneda dolar = new Moneda("FIAT", "Dólar Estadounidense", "USD", 1.0, 0.0, 1000.0, "usd.png");
         

         // Crear usuarios y asignarles cantidades de las monedas
        
         FiduciariaUsuario fiduciario = new FiduciariaUsuario(30000.0, dolar);
         fiduciario.setID_Usuario(this.InicioVista.getUser().getIdPersona());
         
         
         // Guardar usuarios y activos en la base de datos
         FiduciariaUsuarioDAOjdbc fiduciariaDAO = new FiduciariaUsuarioDAOjdbc();
         
         fiduciariaDAO.guardarEnBD(fiduciario);
    
        // Obtener instancia del DAO para monedas desde la fábrica de DAO
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
         ActualizarTabla();
    }
    
    private void handleMisCotizaciones(){
        InicioVista.setVisible(false);
        cotizacionesVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());;
        cotizacionesVista.setVisible(true);
    }



    private void handleExportarCSV() throws SQLException {
    	CriptoUsuarioDAOjdbc criptoUsuario = DAOFactory.getCriptoUsuarioDAO();
    	FiduciariaUsuarioDAOjdbc fiduciaria = DAOFactory.getFiduciariaUsuarioDAO();
    	List<CriptoUsuario> criptos = criptoUsuario.listarActivosCripto();
    	List<FiduciariaUsuario> fiduciarias= fiduciaria.listarActivosFiat();
    	
    	if(ExportarComoCSV.exportarActivosAArchivoCSV(criptos, fiduciarias,  "ARCHIVOCSV")) {
    		DialogoPersonalizado.mostrarDialogo("Archivo CSV generado con éxito." , " Genial!!!"); 
    	}
    	else {
    		DialogoPersonalizado.mostrarDialogo("Hubo un inconveniente al generar el archivo CSV." , " OH NO ☺");
    	}
    	
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
        DefaultTableModel model = (DefaultTableModel) InicioVista.getModel();
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
        InicioVista.getBalanceLabel().setText(String.valueOf(balanceTotal));
    }
    
    public UsuarioActivo getUsuarioActivo() {
    	return user;
    }
}


