package abb;

import dominio.Afiliado;

public class NodoABB {
	
	private Afiliado dato;
	private NodoABB izq;
	private NodoABB der;
	
	//==================  Construct  ==================//
	public NodoABB(Afiliado dato, NodoABB izq, NodoABB der) {
		this.dato = dato;
		this.izq = izq;
		this.der = der;
	}
	public NodoABB(Afiliado dato) {
		this.dato = dato;
	}
	
	//==================  Properties  =================//
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

	@Override
	public String toString() {
		return dato + "";
	}
}
