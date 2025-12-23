package Comparadores;

import java.util.Comparator;

import Modelos.Moneda;

public class ComparadorDeStockPorNomenclatura implements Comparator<Moneda> {

	public int compare(Moneda m1, Moneda m2) {
		return m1.getNomenclatura().compareTo(m2.getNomenclatura());
    }
}
