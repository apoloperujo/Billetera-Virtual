package Comparadores;

import java.util.Comparator;

import Modelos.FiduciariaUsuario;

public class ComparadorDeFiduciariasUsuariosPorCantidad implements Comparator<FiduciariaUsuario> {

	public int compare(FiduciariaUsuario f1, FiduciariaUsuario f2) {
        return Double.compare(f1.getCantidad(), f2.getCantidad());
    }
}
