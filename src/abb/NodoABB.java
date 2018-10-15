package abb;

public class NodoABB {
	
	private int dato;
	private Object objectoDatos;
	private NodoABB izq;
	private NodoABB der;
	
	//==================  Construct  ==================//
	public NodoABB(int dato, NodoABB izq, NodoABB der) {
		this.dato = dato;
		this.izq = izq;
		this.der = der;
	}
	
	public NodoABB(int dato, Object objectoDatos) {
		this.dato = dato;
		this.objectoDatos = objectoDatos;
	}

	//==================  Properties  =================//
	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public NodoABB getIzq() {
		return izq;
	}

	public void setIzq(NodoABB izq) {
		this.izq = izq;
	}

	public NodoABB getDer() {
		return der;
	}

	public void setDer(NodoABB der) {
		this.der = der;
	}

	@Override
	public String toString() {
		return dato + "";
	}
}