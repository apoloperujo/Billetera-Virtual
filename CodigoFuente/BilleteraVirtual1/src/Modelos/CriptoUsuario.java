package Modelos;

import java.util.List;

public class CriptoUsuario {
	private double cantidad;
	private Moneda moneda;
	private List<Blockchain> blockchain;
	private String direccion;
	private int ID_Usuario;
    private int ID;
    private int ID_Moneda;
    private String nomenclatura;
	
    public CriptoUsuario(double cantidad, String nomenclatura, int ID_Usuario, int ID,int ID_Moneda) {
        this.cantidad = cantidad;
        this.nomenclatura = nomenclatura;
        this.ID_Usuario = ID_Usuario;
        this.ID_Moneda = ID_Moneda;
        this.ID = ID;
    }
    
	public CriptoUsuario(double cantidad, Moneda moneda ){
		this.cantidad=cantidad;
		this.moneda=moneda;
	}

	
	// Getter y Setter para cantidad
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Getter y Setter para cripto
    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    // Getter y Setter para blockchain
    public List<Blockchain> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(List<Blockchain> blockchain) {
        this.blockchain = blockchain;
    }

    // Getter y Setter para direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
 // Getter y Setter para ID_Usuario
    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }
    
    public int getID_Moneda() {
        return ID_Moneda;
    }

    public void setID_Moneda(int ID_Moneda) {
        this.ID_Moneda = ID_Moneda;
    }

    // Getter y Setter para ID
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setNomenclatura(String nomenclatura) {
    	this.nomenclatura=nomenclatura;
    }
    
    public String getNomenclatura() {
        return nomenclatura;
    }

}



