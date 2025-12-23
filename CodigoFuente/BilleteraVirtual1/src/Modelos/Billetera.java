package Modelos;
import java.util.List;

/**
 * La clase Billetera representa una billetera digital que almacena criptomonedas y fiduciarias,
 * junto con claves, transacciones, y otros elementos relacionados con las finanzas.
 * 
 * @author Burgueño-Perujo
 * @version 1.0
 * @since 2024
 */
public class Billetera {
    // Atributos
    private List<Activos> activosCripto;
    private List<FiduciariaUsuario> activosFiduciaria;
    private String clavePrivada;
    private String clavePublica;
    private double comision;
    private String Cvu;
    private List<Transaccion> historial;
    private double monto;
    private List<DeFi> plazoFijo;
    private List<AltaBaja> registros;
    /**
     * Constructor de la clase Billetera.
     * 
     * @param activosCripto Lista de criptomonedas de la billetera.
     * @param activosFiduciaria Lista de activos fiduciarios en la billetera.
     * @param clavePrivada Clave privada de la billetera para la autenticación.
     * @param clavePublica Clave pública de la billetera para recibir fondos.
     * @param comision Comisión aplicada a las transacciones de la billetera.
     * @param Cvu Código de Verificación Único (CVU) asociado a los activos fiduciarios.
     * @param historial Historial de transacciones realizadas.
     * @param monto Monto total actual en la billetera.
     * @param plazoFijo Lista de inversiones a plazo fijo activas en la billetera.
     * @param registros Lista de registros de altas y bajas de la billetera.
     */
    public Billetera(List<Activos> activosCripto, List<FiduciariaUsuario> activosFiduciaria, 
                     String clavePrivada, String clavePublica, double comision, String Cvu, 
                     List<Transaccion> historial, double monto, List<DeFi> plazoFijo, 
                     List<AltaBaja> registros) {
        this.activosCripto = activosCripto;
        this.activosFiduciaria = activosFiduciaria;
        this.clavePrivada = clavePrivada;
        this.clavePublica = clavePublica;
        this.comision = comision;
        this.Cvu = Cvu;
        this.historial = historial;
        this.monto = monto;
        this.plazoFijo = plazoFijo;
        this.registros = registros;
    }

    // Getters y Setters


    public List<Activos> getActivosCripto() {
        return activosCripto;
    }

    public void setActivosCripto(List<Activos> activosCripto) {
        this.activosCripto = activosCripto;
    }

    public List<FiduciariaUsuario> getActivosFiduciaria() {
        return activosFiduciaria;
    }

    public void setActivosFiduciaria(List<FiduciariaUsuario> activosFiduciaria) {
        this.activosFiduciaria = activosFiduciaria;
    }

    public String getClavePrivada() {
        return clavePrivada;
    }

    public void setClavePrivada(String clavePrivada) {
        this.clavePrivada = clavePrivada;
    }

    public String getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(String clavePublica) {
        this.clavePublica = clavePublica;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getCvu() {
        return Cvu;
    }

    public void setCvu(String Cvu) {
        this.Cvu = Cvu;
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Transaccion> historial) {
        this.historial = historial;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public List<DeFi> getPlazoFijo() {
        return plazoFijo;
    }

    public void setPlazoFijo(List<DeFi> plazoFijo) {
        this.plazoFijo = plazoFijo;
    }

    public List<AltaBaja> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AltaBaja> registros) {
        this.registros = registros;
    }



}