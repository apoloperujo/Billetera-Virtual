package Java;

import MVC.Controladores.ControladorCompras;
import MVC.Controladores.ControladorCotizaciones;
import MVC.Controladores.ControladorInicio;
import MVC.Controladores.ControladorLogin;
import MVC.Controladores.ControladorOperaciones;
import MVC.Controladores.ControladorRegistro;
import MVC.Controladores.PositionManager;
import MVC.Modelos.*;
import MVC.Vistas.*;
public class Main {

    public static void main(String[] args) {
        // Crear el JFrame principal
        PositionManager positionManager = new PositionManager();

        PrecioCriptoModelo modelo = new PrecioCriptoModelo();

        
        LoginVista loginVista = new LoginVista(positionManager);
        RegistroVista registroVista= new RegistroVista(positionManager);
        InicioVista inicioVista = new InicioVista(positionManager);
        OperacionesVista operacionesVista = new OperacionesVista(positionManager);
        CotizacionesVista cotizacionesVista = new CotizacionesVista(positionManager);
        CompraVista compraVista = new CompraVista(positionManager);


        ControladorLogin controladorLogin= new ControladorLogin(loginVista,registroVista,inicioVista,positionManager, cotizacionesVista);
        
        ControladorRegistro controladorRegistro = new ControladorRegistro(loginVista,registroVista,positionManager);
        
        ControladorInicio controladorInicio = new ControladorInicio( loginVista,
        		inicioVista, operacionesVista,  positionManager ,  cotizacionesVista);
        
        ControladorOperaciones ControladorOperaciones = new ControladorOperaciones(loginVista,inicioVista,
        operacionesVista,positionManager);

        ControladorCotizaciones controladorCotizaciones = new ControladorCotizaciones(loginVista,cotizacionesVista,
        inicioVista,compraVista,positionManager,modelo);

        ControladorCompras controladorCompra = new ControladorCompras(loginVista,compraVista,cotizacionesVista,positionManager,inicioVista);
        // Mostrar el frame
        loginVista.setVisible(true);
        
    }
}