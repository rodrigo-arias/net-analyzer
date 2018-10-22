package abb;

import dominio.Afiliado;

public class NodoABB {

	private Afiliado dato;
	private NodoABB izq;
	private NodoABB der;
	private int recorridos;

	// ================== Construct ==================//
	public NodoABB(Afiliado dato, NodoABB izq, NodoABB der, int recorridos) {
		this.dato = dato;
		this.izq = izq;
		this.der = der;
		this.recorridos = recorridos;
	}

	public NodoABB(Afiliado dato) {
		this.dato = dato;
	}

	// ================== Properties =================//
	public Afiliado getDato() {
		return dato;
	}

	public void setDato(Afiliado dato) {
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
	
	public int getRecorridos() {
		return recorridos;
	}

	public void setRecorridos(int recorridos) {
		this.recorridos = recorridos;
	}

	@Override
	public String toString() {
		return dato.getCedula() + ";" + dato.getNombre() + ";" + dato.getEmail();
	}
}
