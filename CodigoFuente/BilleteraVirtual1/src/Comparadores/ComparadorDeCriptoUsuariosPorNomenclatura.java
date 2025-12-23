package Comparadores;
import Modelos.CriptoUsuario;
import java.util.Comparator;

public class ComparadorDeCriptoUsuariosPorNomenclatura implements Comparator<CriptoUsuario>{
	
	
	public int compare(CriptoUsuario c1, CriptoUsuario c2) {
		return c1.getNomenclatura().compareTo(c2.getNomenclatura());
	}
	

}
