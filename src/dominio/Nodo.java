package dominio;

import grafo.Punto;

public class Nodo extends Punto{

	// ================== Attributes =================//
	String nodoId;

	// ================== Construct ==================//
	public Nodo(double coordX, double coordY, String nodoId) {
		super(coordX, coordY);
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
