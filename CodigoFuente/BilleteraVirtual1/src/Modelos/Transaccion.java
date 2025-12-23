package Modelos;



import java.time.LocalDateTime;

public class Transaccion {
	private int ID;
	private int ID_Usuario;
    private Blockchain blockchain;
    private double cantidad;
    private String destino;
    private String estado;
    private CriptoUsuario criptoUsuario;
    private String origen;
    private String tipo;
    private LocalDateTime fecha_hora;
    private String resumen;
    
    
    // Constructor vacío
    public Transaccion() {}
    
    public Transaccion(String resumen, LocalDateTime fecha_hora) {
		this.fecha_hora = fecha_hora;
		this.resumen=resumen;
}
    
    public Transaccion(int ID, int ID_Usuario, String resumen, LocalDateTime fecha_hora) {
			this.fecha_hora = fecha_hora;
			this.resumen=resumen;
			this.ID=ID;
			this.ID_Usuario=ID_Usuario;
	}

    public Transaccion(Blockchain blockchain, double cantidad, String destino, String estado, 
    		CriptoUsuario criptoUsuario, String origen, String tipo, LocalDateTime fecha_hora, String resumen) {
			this.blockchain = blockchain;
			this.cantidad = cantidad;
			this.destino = destino;
			this.estado = estado;
			this.criptoUsuario = criptoUsuario;
			this.origen = origen;
			this.tipo = tipo;
			this.fecha_hora = fecha_hora;
			this.resumen=resumen;
	}
   
    public Transaccion(CriptoUsuario criptoUsuario) {
			this.criptoUsuario=criptoUsuario;

	}
    // Getters y Setters
    public Blockchain getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(Blockchain blockchain) {
        this.blockchain = blockchain;
    }
    
    

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CriptoUsuario getCriptoUsuario() {
        return criptoUsuario;
    }

    public void setCriptoUsuario(CriptoUsuario criptoUsuario) {
        this.criptoUsuario = criptoUsuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
 // Setter
    public void setFecha_hora(LocalDateTime fecha_hora) {
        // Si el valor pasado es null, asigna la fecha y hora actuales
        if (fecha_hora == null) {
            this.fecha_hora = LocalDateTime.now();
        } else {
            this.fecha_hora = fecha_hora;
        }
    }

    // Getter
    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    // Getter y Setter para resumen
    public String getResumen() {
        return resumen;
    }

    // Setter para resumen
    public void setResumen(String resumen) {
    	this.resumen=resumen;
    }

    // Método calcularCant
    public double calcularCant(Moneda monedaOrigen, Moneda monedaDestino) {
        return 0.0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═════════════════════════════════════════════════════════╗" + "\n");
        sb.append("║ Resumen: ").append(resumen).append("\n");
        sb.append("║ Fecha y hora: ").append(fecha_hora).append("\n");
        sb.append("╚═════════════════════════════════════════════════════════╝");
        return sb.toString();
    }


    
    

}
