package DAO;

import Modelos.Compra;
import Modelos.CriptoUsuario;
import Modelos.FiduciariaUsuario;
import Modelos.Moneda;
import Modelos.Swap;
import Modelos.Transaccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Java.MyConnection;

public class TransaccionesDAOjdbc implements TransaccionesDAO{
	
	
	private boolean guardarEnBD(Transaccion transaccion) throws SQLException {
		Connection connection= MyConnection.getCon();
    	
		
    	String sql = "INSERT INTO TRANSACCION (RESUMEN , FECHA_HORA, ID_USUARIO) VALUES (?, ?, ?)";
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    		stmt.setString(1,transaccion.getResumen() );
    		 stmt.setTimestamp(2, Timestamp.valueOf(transaccion.getFecha_hora()));
    		 stmt.setInt(3,transaccion.getCriptoUsuario().getID_Usuario());

    		stmt.executeUpdate();
    		return true; // Se guardó correctamente
    	} catch (SQLException e) {
    		System.out.println("Error al guardar en la base de datos: " + e.getMessage());
    		return false; // Ocurrió un error al guardar
    	}
	}
	
	
	public List<Transaccion> listarTransacciones() throws SQLException {
	    List<Transaccion> listaTransacciones = new ArrayList<>();

	    Connection connection = MyConnection.getCon();

	    // Consulta SQL para obtener todos los datos de la tabla "TRANSACCION"
	    String sql = "SELECT * FROM TRANSACCION";

	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        // Recorre los resultados e inserta en la lista
	        while (rs.next()) {
	            String resumen = rs.getString("RESUMEN");
	            LocalDateTime fecha_hora = rs.getTimestamp("FECHA_HORA").toLocalDateTime();

	            Transaccion transaccion = new Transaccion(resumen, fecha_hora);

	            listaTransacciones.add(transaccion);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al listar las transacciones: " + e.getMessage());
	    }

	    return listaTransacciones;
	}
	
	public boolean Compra(Compra compra )throws SQLException{	
		
		FiduciariaUsuario fiduciaria=compra.getFiduciaria();
		CriptoUsuario cripto=compra.getCriptoUsuario();

		
		MonedaDAOjdbc moneda= new MonedaDAOjdbc(); 
		FiduciariaUsuarioDAOjdbc fiduciariaUsuario= new FiduciariaUsuarioDAOjdbc();
		CriptoUsuarioDAOjdbc criptoUsuario = new CriptoUsuarioDAOjdbc();
		
		if(!criptoUsuario.Existe(cripto.getNomenclatura())) {
			CriptoUsuario criptousuario=new CriptoUsuario(0,cripto.getMoneda());
			criptoUsuario.guardarEnBD(criptousuario);
		}
		
		if(!fiduciariaUsuario.Existe(fiduciaria.getNomenclatura())) {
			FiduciariaUsuario fiduciariausuario=new FiduciariaUsuario(0,fiduciaria.getMoneda());
			fiduciariaUsuario.guardarEnBD(fiduciariausuario);
		}
		
		
		double stock_Fiat = fiduciariaUsuario.obtenerStockFiatUsuario(fiduciaria.getNomenclatura());
		if(stock_Fiat < compra.getFiduciaria().getCantidad()) {
			System.out.println("ERROR: no hay suficiente stock de la moneda fiduciaria para realizar la transaccion");
			return false;
		}
		compra.setFiduciariaStock(true);
		double Stock_Sistema_cripto= moneda.obtenerStockMoneda(cripto.getNomenclatura());
		if(Stock_Sistema_cripto < compra.getCriptoUsuario().getCantidad()) {
			System.out.println("ERROR: no hay suficiente stock de la criptomoneda para realizar la transaccion");
			return false;
		}
		compra.setCriptoStock(true);
		//modifica el stock de cripto del sistema
		double NuevoStock_Sistema_cripto= Stock_Sistema_cripto - compra.getCriptoUsuario().getCantidad();
		moneda.modificarCantidadMoneda(cripto.getNomenclatura(), NuevoStock_Sistema_cripto);
		
		//modifica el stock de FIAT del sistema
		double Stock_Sistema_Fiat=moneda.obtenerStockMoneda(fiduciaria.getNomenclatura());
		double NuevoStock_Sistema_Fiat=Stock_Sistema_Fiat + compra.getFiduciaria().getCantidad();
		moneda.modificarCantidadMoneda(fiduciaria.getNomenclatura(), NuevoStock_Sistema_Fiat);
		
		//modifica el stock de FIAT del usuario
		double nuevo_stockFiatUsuario = stock_Fiat - compra.getFiduciaria().getCantidad();
		fiduciariaUsuario.modificarCantidadFiatUsuario(fiduciaria.getNomenclatura(), nuevo_stockFiatUsuario);
		
		//modifica el stock de Cripto del usuario
		double stock_Cripto=criptoUsuario.obtenerStockCriptoUsuario(cripto.getNomenclatura());
		double nuevo_stockCriptoUsuario=stock_Cripto + compra.getCriptoUsuario().getCantidad();
		
		criptoUsuario.modificarCantidadCriptoUsuario(cripto.getNomenclatura(), nuevo_stockCriptoUsuario);
		
		
		// Crear una fecha y hora para la compra
		LocalDateTime fechaHora = LocalDateTime.of(2024, 11, 1, 10, 15);
		compra.setFecha_hora(fechaHora);

		// Formatear el resumen de manera clara y consistente
		String resumen = String.format(
		    "(de %s hacia %s)    %.2f ---> %.2f",
		    fiduciaria.getNomenclatura(),
		    cripto.getNomenclatura(),
		    fiduciaria.getCantidad(),
		    cripto.getCantidad()
		);
		compra.setResumen(resumen);

		// Guardar la compra en la base de datos
		this.guardarEnBD(compra);

		return true;
	}

	
	public boolean Swap(Swap swap)throws SQLException {
		
		CriptoUsuarioDAOjdbc criptoUsuario = new CriptoUsuarioDAOjdbc();
		MonedaDAOjdbc moneda = new MonedaDAOjdbc();
		
		Moneda criptoEsperada= swap.getCriptoEsperada().getMoneda();
		Moneda criptoConvertir= swap.getCriptoUsuario().getMoneda();
		
		if(!criptoUsuario.Existe(criptoEsperada.getNomenclatura())) {
			CriptoUsuario criptousuarioE=new CriptoUsuario(0,criptoEsperada);
			criptoUsuario.guardarEnBD(criptousuarioE);
		}
		if(!criptoUsuario.Existe(criptoConvertir.getNomenclatura())) {
			CriptoUsuario criptousuarioC=new CriptoUsuario(0,criptoConvertir);
			criptoUsuario.guardarEnBD(criptousuarioC);
		}
		
		double Stock_Sistema_criptoE = moneda.obtenerStockMoneda(criptoEsperada.getNomenclatura());
		if(Stock_Sistema_criptoE < swap.getCriptoEsperada().getCantidad()) {
			System.out.println("ERROR: no hay suficiente stock de la criptomoneda a recibir para realizar la transaccion");
			return false;
		}
		double Stock_Usuario_criptoC = criptoUsuario.obtenerStockCriptoUsuario(criptoConvertir.getNomenclatura());
		if(Stock_Usuario_criptoC < swap.getCriptoUsuario().getCantidad()) {
			System.out.println("ERROR: no hay suficiente stock de la criptomoneda a enviar para realizar la transaccion");
			return false;
		}
	
		//modifica el stock de cripto esperada del sistema
		double NuevoStock_Sistema_criptoE= Stock_Sistema_criptoE - swap.getCriptoEsperada().getCantidad();
		moneda.modificarCantidadMoneda(criptoEsperada.getNomenclatura(), NuevoStock_Sistema_criptoE);
		
		//modifica el stock de cripto convertir del sistema
		double Stock_Sistema_criptoC= moneda.obtenerStockMoneda(criptoConvertir.getNomenclatura());
		double NuevoStock_Sistema_criptoC = Stock_Sistema_criptoC + swap.getCriptoUsuario().getCantidad();
		moneda.modificarCantidadMoneda(criptoConvertir.getNomenclatura(), NuevoStock_Sistema_criptoC);
		
		//modifica el stock de cripto a convertir del usuario
		double NuevoStock_Usuario_criptoC = Stock_Usuario_criptoC - swap.getCriptoUsuario().getCantidad();
		criptoUsuario.modificarCantidadCriptoUsuario(criptoConvertir.getNomenclatura(), NuevoStock_Usuario_criptoC);
		
		//modifica el stock de cripto a esperada del usuario
		double Stock_Usuario_criptoE = criptoUsuario.obtenerStockCriptoUsuario(criptoEsperada.getNomenclatura());
		double NuevoStock_Usuario_criptoE = Stock_Usuario_criptoE + swap.getCriptoEsperada().getCantidad();
		criptoUsuario.modificarCantidadCriptoUsuario(criptoEsperada.getNomenclatura(), NuevoStock_Usuario_criptoE);

		
		swap.setFecha_hora(LocalDateTime.of(2024, 11, 1, 10, 15));
		swap.setResumen("Swap completado de la criptomoneda: " + criptoConvertir.getNomenclatura() + " hacia la " + criptoEsperada.getNomenclatura());
		guardarEnBD(swap);
		
		return true;
	}




}
