package dominio;

import grafo.Punto;

public class Canalera extends Punto {

	String chipId;
	String CIafiliado;

	// ================== Construct ==================//
	public Canalera(double coordX, double coordY, String chipId, String CIafiliado) {
		super(coordX, coordY);
		this.chipId = chipId;
		this.CIafiliado = CIafiliado;
	}

	// ================== Properties =================//
	public String getChipId() {
		return chipId;
	}

	public void setChipId(String chipId) {
		this.chipId = chipId;
	}

	public String getCIafiliado() {
		return CIafiliado;
	}

	public void setCIafiliado(String cIafiliado) {
		CIafiliado = cIafiliado;
	}
}
