package Modelos;

public class FiduciariaUsuario {
	private double cantidad;
	private Moneda moneda;
	private int ID_Usuario;
    private int ID;
    private int ID_Moneda;
    private String nomenclatura;
    
    
    public FiduciariaUsuario(double cantidad, String nomenclatura, int ID_Usuario, int ID,int ID_Moneda) {
        this.cantidad = cantidad;
        this.nomenclatura = nomenclatura;
        this.ID_Usuario = ID_Usuario;
        this.ID_Moneda = ID_Moneda;
        this.ID = ID;
    }
    
    public FiduciariaUsuario(double cantidad, Moneda moneda, int ID_Usuario, int ID, int ID_Moneda, String nomenclatura) {
        this.cantidad = cantidad;
        this.moneda = moneda;
        this.ID_Usuario = ID_Usuario;
        this.ID = ID;
        this.ID_Moneda = ID_Moneda;
        this.nomenclatura = nomenclatura;
    }


    
    public FiduciariaUsuario(double cantidad, Moneda moneda) {
        this.cantidad = cantidad;
        this.moneda = moneda;
    }
	

    public FiduciariaUsuario() {
		// TODO Auto-generated constructor stub
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
    
    public void setNomenclatura(String nomenclatura) {
    	this.nomenclatura=nomenclatura;
    }
    
    public String getNomenclatura() {
        return nomenclatura;
    }


}