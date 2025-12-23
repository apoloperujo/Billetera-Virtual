package Comparadores;

import java.util.Comparator;

import Modelos.CriptoUsuario;

public class ComparadorDeCriptoUsuariosPorCantidad implements Comparator<CriptoUsuario> {

    public int compare(CriptoUsuario c1, CriptoUsuario c2) {
        return Double.compare(c1.getCantidad(), c2.getCantidad());
    }
}