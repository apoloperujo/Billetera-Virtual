package Modelos;

import java.util.List;


public class Activos {
	private List<Blockchain> blockchains;
    private double cantidad;
    private String direccion;
    private Moneda moneda; 
    
    public Activos(double cantidad, String direccion, Moneda moneda,List<Blockchain> blockchains) {
        this.cantidad = cantidad;
        this.direccion = direccion;
        this.moneda = moneda;
        this.blockchains=blockchains;
    }
    
 // Getter y Setter para cantidad
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter para moneda
    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    // Getter y Setter para blockchains
    public List<Blockchain> getBlockchains() {
        return blockchains;
    }

    public void setBlockchain(List<Blockchain> blockchains) {
        this.blockchains = blockchains;
    }

    // Getter y Setter para direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
