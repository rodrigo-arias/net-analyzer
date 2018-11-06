package dominio;

public class Nodo {

	String nodoId;

	// ================== Construct ==================//
	public Nodo(String nodoId) {
		this.nodoId = nodoId;
	}

	// ================== Properties =================//
	public String getNodoId() {
		return nodoId;
	}

	public void setNodoId(String nodoId) {
		this.nodoId = nodoId;
	}

}
