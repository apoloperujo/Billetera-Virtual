package Comparadores;

import java.util.Comparator;

import Modelos.FiduciariaUsuario;

public class ComparadorDeFiduciariasUsuariosPorNomenclatura implements Comparator<FiduciariaUsuario> {

	public int compare(FiduciariaUsuario f1, FiduciariaUsuario f2) {
		return f1.getNomenclatura().compareTo(f2.getNomenclatura());
    }
}
