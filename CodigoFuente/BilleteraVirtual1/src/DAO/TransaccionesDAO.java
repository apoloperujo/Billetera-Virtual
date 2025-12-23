package DAO;


import java.sql.SQLException;
import java.util.List;

import Modelos.Compra;
import Modelos.Swap;
import Modelos.Transaccion;

public interface TransaccionesDAO {
	

	boolean Swap(Swap swap)throws SQLException;
	
	boolean Compra(Compra compra )throws SQLException;
	
	List<Transaccion> listarTransacciones() throws SQLException;
}
