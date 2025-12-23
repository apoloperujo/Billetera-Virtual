package Modelos;

public class Compra extends Transaccion{
	private FiduciariaUsuario fiduciaria;
	private boolean fiduciariaStock;
	private boolean criptoStock;

	public Compra(FiduciariaUsuario fiduciaria,CriptoUsuario cripto) {
		super(cripto);
		this.fiduciaria=fiduciaria;
		
	}

	// Getter y Setter para fiduciaria
	public FiduciariaUsuario getFiduciaria() {
	    return fiduciaria;
	}

	public void setFiduciaria(FiduciariaUsuario fiduciaria) {
	    this.fiduciaria = fiduciaria;
	}
	public boolean isFiduciariaStock() {
		return fiduciariaStock;
	}

	public void setFiduciariaStock(boolean fiduciariaStock) {
		this.fiduciariaStock = fiduciariaStock;
	}

	public boolean isCriptoStock() {
		return criptoStock;
	}

	public void setCriptoStock(boolean criptoStock) {
		this.criptoStock = criptoStock;
	}
}
