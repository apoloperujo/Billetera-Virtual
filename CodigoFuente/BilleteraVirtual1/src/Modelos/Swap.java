package Modelos;

public class Swap extends Transaccion{

	private CriptoUsuario criptoEsperada;
	
	public Swap(CriptoUsuario criptoConvertir, CriptoUsuario criptoEsperada) {
		super(criptoConvertir);
		this.criptoEsperada=criptoEsperada;
	}
	

	
	// Getter y Setter para cantidad2
	public CriptoUsuario getCriptoEsperada() {
	    return criptoEsperada;
	}

	public void setCriptoEsperada(CriptoUsuario criptoEsperada) {
	    this.criptoEsperada = criptoEsperada;
	}

}
