package Modelos;


public class Moneda {

    private String nombre;
    private String nomenclatura;
    private double valorDolar;
    private Double volatilidad; // Puede ser null para monedas fiat
    private Double stock;
    private String nombreIcono;
    private int ID;
    private String tipo;
    
 // Constructor
    public Moneda(int ID, String tipo, String nombre, String nomenclatura, double valorDolar, Double volatilidad, Double stock, String nombreIcono) {
		  this.ID = ID;
		  this.tipo = tipo;
		  this.nombre = nombre;
		  this.nomenclatura = nomenclatura;
		  this.valorDolar = valorDolar;
		  this.volatilidad = volatilidad;
		  this.stock = stock;
		  this.nombreIcono = nombreIcono;
	}
    
    public Moneda(String tipo, String nomenclatura, double valorDolar, Double stock) {
		  this.tipo = tipo;
		  this.nomenclatura = nomenclatura;
		  this.valorDolar = valorDolar;
		  this.stock = stock;
	}
    
    
    public Moneda(String tipo, String nombre, String nomenclatura, double valorDolar, Double volatilidad, Double stock, String nombreIcono) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.valorDolar = valorDolar;
        this.volatilidad = volatilidad;
        this.stock = stock;
        this.nombreIcono=nombreIcono;
    }
    
    public Moneda(double valorDolar) {
        this.valorDolar = valorDolar;
    }

    
    public Moneda(String nomenclatura) {
    	this.nomenclatura=nomenclatura;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public double getValorDolar() {
        return valorDolar;
    }

    public Double getVolatilidad() {
        return volatilidad;
    }

    public Double getStock() {
        return stock;
    }
    
    public String getNombreIcono() {
        return nombreIcono;
    }
    
    public void setNomenclatura(String nomenclatura) {
    	this.nomenclatura=nomenclatura;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
