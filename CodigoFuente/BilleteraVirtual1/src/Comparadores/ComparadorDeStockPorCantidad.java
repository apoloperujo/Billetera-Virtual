package Comparadores;

import java.util.Comparator;

import Modelos.Moneda;

public class ComparadorDeStockPorCantidad implements Comparator<Moneda>{
	
	public int compare(Moneda m1, Moneda m2) {
        return Double.compare(m1.getStock(), m2.getStock());
    }

}
