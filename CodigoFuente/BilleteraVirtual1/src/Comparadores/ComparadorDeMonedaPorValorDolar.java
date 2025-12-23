package Comparadores;

import java.util.Comparator;

import Modelos.Moneda;

public class ComparadorDeMonedaPorValorDolar implements Comparator<Moneda> {

	public int compare(Moneda m1, Moneda m2) {
        return Double.compare(m1.getValorDolar(), m2.getValorDolar());
    }
}