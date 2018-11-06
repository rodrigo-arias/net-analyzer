package dominio;

public class Canalera {
	
	String chipId;
	String CIafiliado;

	//==================  Construct  ==================//
	public Canalera(String chipId, String CIafiliado) {
		this.chipId = chipId;
		this.CIafiliado = CIafiliado;
	}
	
	public Canalera() {
	}

	//==================  Properties  =================//
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
