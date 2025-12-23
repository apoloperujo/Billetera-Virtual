package MVC.Controladores;

import MVC.Vistas.CompraVista;
import MVC.Vistas.CotizacionesVista;
import MVC.Vistas.InicioVista;
import MVC.Vistas.LoginVista;
import MVC.Vistas.Componentes.DialogoPersonalizado;
import MVC.Modelos.PrecioCriptoModelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import org.json.JSONObject;

import DAO.DAOFactory;
import DAO.MonedaDAOjdbc;

import java.util.Timer;
import java.util.TimerTask;


public class ControladorCotizaciones {
    private final PositionManager positionManager;

    private InicioVista InicioVista;
    private CotizacionesVista CotizacionesVista;
    private CompraVista CompraVista;
    private LoginVista LoginVista;
    private UsuarioActivo user;

    private PrecioCriptoModelo modelo;
    private Timer timer;

    public ControladorCotizaciones(LoginVista LoginVista,CotizacionesVista CotizacionesVista,
        InicioVista InicioVista,CompraVista CompraVista,PositionManager positionManager, PrecioCriptoModelo modelo) {
            this.positionManager = positionManager;
            this.CotizacionesVista = CotizacionesVista;
            this.InicioVista = InicioVista;
            this.CompraVista = CompraVista;
            this.LoginVista = LoginVista;

            this.modelo = modelo;
            this.timer = new Timer();

            cargarPrecios();

            this.CotizacionesVista.getCerrarSesionBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCerrarSesionAction();
                }
            });
            this.CotizacionesVista.getVolverBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleVolver();
                }
            });
            
            this.CotizacionesVista.getComprarBTC().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleComprarBTC();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                }
            });
            
            this.CotizacionesVista.getComprarETH().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleComprarETH();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                }
            });
            
            this.CotizacionesVista.getComprarUSDC().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleComprarUSDC();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                }
            });
            
            this.CotizacionesVista.getComprarUSDT().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleComprarUSDT();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                }
            });
            
            this.CotizacionesVista.getComprarDOGE().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
						handleComprarDOGE();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                }
            });
    }

    private void handleCerrarSesionAction() {
        CotizacionesVista.setVisible(false);
        LoginVista.getEmailCampo().resetear();
        LoginVista.getContraCampo().resetear();
        DialogoPersonalizado.mostrarDialogo("Sesión cerrada con éxito.", "Te vamos a extrañar :(");  
        LoginVista.setVisible(true);
    }

    private void cargarPrecios() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject precios = modelo.obtenerPreciosCripto();
                    

                    double btc_Precio = precios.getJSONObject("bitcoin").getDouble("usd");
                    double eth_Precio = precios.getJSONObject("ethereum").getDouble("usd");
                    double usdc_Precio = precios.getJSONObject("usd-coin").getDouble("usd");
                    double usdt_Precio = precios.getJSONObject("tether").getDouble("usd");
                    double doge_Precio = precios.getJSONObject("dogecoin").getDouble("usd");

                    String btc = String.valueOf(btc_Precio);
                    String eth = String.valueOf(eth_Precio);
                    String usdc = String.valueOf(usdc_Precio);
                    String usdt = String.valueOf(usdt_Precio);
                    String doge = String.valueOf(doge_Precio);
    
                    CotizacionesVista.actualizarPrecios(btc, eth, usdc, usdt, doge);
                    MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
                    monedaDAO.modificarValorMoneda("BTC", btc_Precio);
                    monedaDAO.modificarValorMoneda("ETH", eth_Precio);
                    monedaDAO.modificarValorMoneda("USDC", usdc_Precio);
                    monedaDAO.modificarValorMoneda("USDT", usdt_Precio);
                    monedaDAO.modificarValorMoneda("DOGE", doge_Precio);
    
                } catch (IOException | InterruptedException | SQLException e) {
                    e.printStackTrace();
                    DialogoPersonalizado.mostrarDialogo("No se pudo obtener precio de criptomonedas, reintentando en segundo plano.", "ERROR");
                }
            }
        }, 1000, 30000); 
    }

    private void handleVolver() {
        CotizacionesVista.setVisible(false);
        InicioVista.setVisible(true);
    }

    private void handleComprarBTC() throws SQLException {
        CotizacionesVista.setVisible(false);
        CompraVista.setMonedaActual("BTC");
        CompraVista.getPrecioCompra().setText(CotizacionesVista.getBtcPrecio().getText());
        CompraVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());
        MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
        CompraVista.getStockDisponible().setText(String.valueOf(monedaDAO.obtenerStockMoneda("BTC")));
        CompraVista.setVisible(true);
    }

    private void handleComprarETH() throws SQLException {
        CotizacionesVista.setVisible(false);
        CompraVista.setMonedaActual("ETH");
        CompraVista.getPrecioCompra().setText(CotizacionesVista.getEthPrecio().getText());
        CompraVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());
        MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
        CompraVista.getStockDisponible().setText(String.valueOf(monedaDAO.obtenerStockMoneda("ETH")));
        CompraVista.setVisible(true);
    }

    private void handleComprarUSDC() throws SQLException {
        CotizacionesVista.setVisible(false);
        CompraVista.setMonedaActual("USDC");
        CompraVista.getPrecioCompra().setText(CotizacionesVista.getUsdcPrecio().getText());
        CompraVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());
        MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
        CompraVista.getStockDisponible().setText(String.valueOf(monedaDAO.obtenerStockMoneda("USDC")));
        CompraVista.setVisible(true);
    }

    private void handleComprarUSDT() throws SQLException {
        CotizacionesVista.setVisible(false);
        CompraVista.setMonedaActual("USDT");
        CompraVista.getPrecioCompra().setText(CotizacionesVista.getUsdtPrecio().getText());
        CompraVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());
        MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
        CompraVista.getStockDisponible().setText(String.valueOf(monedaDAO.obtenerStockMoneda("USDT")));
        CompraVista.setVisible(true);
    }

    private void handleComprarDOGE() throws SQLException {
        CotizacionesVista.setVisible(false);
        CompraVista.setMonedaActual("DOGE");
        CompraVista.getPrecioCompra().setText(CotizacionesVista.getDogePrecio().getText());
        CompraVista.getNombreLabel().setText(this.InicioVista.getNombreLabel().getText());
        MonedaDAOjdbc monedaDAO = DAOFactory.getMonedaDAO();
        CompraVista.getStockDisponible().setText(String.valueOf(monedaDAO.obtenerStockMoneda("DOGE")));
        CompraVista.setVisible(true);
    }

    public void ModificarNombre() {
        this.InicioVista.setLabelPersonalizado(this.user.getPersona().getNombre());
    }
}
