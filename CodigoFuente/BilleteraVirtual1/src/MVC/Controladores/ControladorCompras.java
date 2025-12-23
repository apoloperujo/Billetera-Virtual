package MVC.Controladores;

import MVC.Excepciones.ComprasExcepcion;
import MVC.Excepciones.RegistroExcepcion;
import MVC.Vistas.CompraVista;
import MVC.Vistas.CotizacionesVista;
import MVC.Vistas.InicioVista;
import MVC.Vistas.LoginVista;
import MVC.Vistas.RegistroVista;
import MVC.Vistas.Componentes.DialogoPersonalizado;
import MVC.Vistas.Componentes.LabelPersonalizado;
import Modelos.Compra;
import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;
import Modelos.Moneda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import DAO.CriptoUsuarioDAOjdbc;
import DAO.DAOFactory;
import DAO.FiduciariaUsuarioDAOjdbc;
import DAO.MonedaDAO;
import DAO.TransaccionesDAOjdbc;

public class ControladorCompras {
    private final PositionManager positionManager;

    private LoginVista LoginVista;
    private CompraVista CompraVista;
    private CotizacionesVista CotizacionesVista;
    private InicioVista inicioVista;

    public ControladorCompras(LoginVista LoginVista,
         CompraVista CompraVista, CotizacionesVista CotizacionesVista,
        PositionManager positionManager,  InicioVista inicioVista) {
            this.positionManager = positionManager;
            this.inicioVista=inicioVista;
            this.LoginVista = LoginVista;
            this.CompraVista = CompraVista;
            this.CotizacionesVista = CotizacionesVista;
            
            // Acción del botón de Login
            this.CompraVista.getCerrarSesionBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCerrarSesionAction();
                }
            });
            this.CompraVista.getCancelarBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCancelarAction();
                }
            });
            
            this.CompraVista.getConfirmarBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        handleConfirmarAction();
                    } 
                 catch (SQLException e1) {
                    e1.printStackTrace();
                }catch (ComprasExcepcion ex) {
                        DialogoPersonalizado.mostrarDialogo(ex.getMessage(), "ERROR");
                }  
                }
            });
            
            this.CompraVista.getConvertirBoton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleConvertirBottonAction();
                }
            });
        }
        private void handleCerrarSesionAction() {
            
            CompraVista.setVisible(false);
            LoginVista.getEmailCampo().resetear();
            LoginVista.getContraCampo().resetear();
            DialogoPersonalizado.mostrarDialogo("Sesión cerrada con éxito.", "Te vamos a extrañar :(");  
            LoginVista.setVisible(true);

        }
        private void handleCancelarAction() {
        	CompraVista.getCantidadDisponible().resetear();
            CompraVista.setVisible(false);
            CotizacionesVista.setVisible(true);

        }

        private void handleConfirmarAction() throws SQLException, ComprasExcepcion {
            String CantidadEnTexto = CompraVista.getCantidadDisponible().getText();
            
            if(CantidadEnTexto.equals("") || CantidadEnTexto.equals("Ej: 10.000" ) || CantidadEnTexto.equals("0" )){
                this.CompraVista.getCantidadDisponible().campoRed();
                throw new ComprasExcepcion("Por favor, ingrese una cantidad valida para la compra.");
                
            }
            double CantidadAComprar = Double.parseDouble(CantidadEnTexto);
            FiduciariaUsuarioDAOjdbc fiduciaria = DAOFactory.getFiduciariaUsuarioDAO();
            CriptoUsuarioDAOjdbc criptos = DAOFactory.getCriptoUsuarioDAO();
            FiduciariaUsuario fiduciariaUsuario = fiduciaria.obtenerFiduciariaPorNomenclatura("USD");
            CriptoUsuario criptoUsuario = criptos.obtenerCriptoPorNomenclatura(CompraVista.getMonedaActual());
            TransaccionesDAOjdbc transaccion = new TransaccionesDAOjdbc();
            fiduciariaUsuario.setID_Usuario(inicioVista.getUser().getIdPersona());
            criptoUsuario.setID_Usuario(inicioVista.getUser().getIdPersona());
            fiduciariaUsuario.setCantidad(CantidadAComprar);
            criptoUsuario.setCantidad(calcular(CompraVista.getMonedaActual(), CantidadAComprar));
            
            Compra compra = new Compra(fiduciariaUsuario, criptoUsuario);
            
            if (transaccion.Compra(compra)) {
                ActualizarTabla();
                DialogoPersonalizado.mostrarDialogo("Compra realizada con exito.", "Felicitaciones :)");
            } else {
                if (!compra.isFiduciariaStock()) {
                    throw new ComprasExcepcion("Saldo insuficiente para completar la compra.");
                }
                
                if (!compra.isCriptoStock() ) {
                    throw new ComprasExcepcion("No hay suficiente stock de la moneda para realizar la compra.");
                }
            }
            CompraVista.getCantidadDisponible().resetear();
            CompraVista.setVisible(false);
            inicioVista.setVisible(true);
        }
        
        
        private double convertirLabelADouble(LabelPersonalizado label) {
            if (label == null || label.getText() == null || label.getText().isEmpty()) {
                throw new IllegalArgumentException("El label o su texto son nulos o están vacíos.");
            }

            try {
                return Double.parseDouble(label.getText());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("El texto del label no se puede convertir a un número: " + label.getText());
            }
        }
        
        private double calcular(String moneda, double numero) {
            switch (moneda) {
                case "BTC":
                    return numero / convertirLabelADouble(CotizacionesVista.getBtcPrecio());
                case "USDC":
                    return numero / convertirLabelADouble(CotizacionesVista.getUsdcPrecio());
                case "USDT":
                    return numero / convertirLabelADouble(CotizacionesVista.getUsdtPrecio());
                case "DOGE":
                    return numero / convertirLabelADouble(CotizacionesVista.getDogePrecio());
                case "ETH":
                    return numero / convertirLabelADouble(CotizacionesVista.getEthPrecio());
                default:
                    throw new IllegalArgumentException("Moneda no reconocida: " + moneda);
            }
        }
        
        public void handleConvertirBottonAction(){
        	String CantidadEnTexto= CompraVista.getCantidadDisponible().getText();
        	double CantidadAComprar = Double.parseDouble(CantidadEnTexto);
        	double cantidad= calcular(CompraVista.getMonedaActual(),CantidadAComprar);
        	CompraVista.getCantidadComprarLabel().setText("Cantidad a recibir:" + cantidad);
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
        
        
}
