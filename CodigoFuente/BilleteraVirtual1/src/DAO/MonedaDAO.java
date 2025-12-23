package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelos.Moneda;

public interface MonedaDAO {
	
	
	boolean Existe( String nomenclatura) throws SQLException;

	void guardarEnBD( Moneda moneda) throws SQLException;
	
	List<Moneda> listarMonedas( ) throws SQLException;
	
	void generarStockAleatorio() throws SQLException;

	boolean modificarCantidadMoneda(String nomenclatura, double nuevaCantidad) throws SQLException;
	
	boolean modificarValorMoneda(String nomenclatura, double nuevoValorDolar) throws SQLException;
	
	double obtenerPrecioMoneda(String nomenclatura) throws SQLException;

}
