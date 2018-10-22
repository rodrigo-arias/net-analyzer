package abb;

import dominio.Afiliado;

public class ABB {

	private NodoABB raiz;

	// ================== Construct ==================//
	public ABB() {
		this.raiz = null;
	}

	public ABB(NodoABB raiz) {
		this.raiz = raiz;
	}

	// ================== Properties =================//
	public NodoABB getRaiz() {
		return raiz;
	}

	// =================== Methods ==================//
	public NodoABB buscar(String dato) {
		return buscarRec(dato, raiz, 0);
	}

	private NodoABB buscarRec(String dato, NodoABB nodo, int recorridos) {
		if (nodo == null)
			return null;
		else {
			int compare = dato.compareTo(nodo.getDato().getCedula());

			if (compare == 0) {
				nodo.setRecorridos(recorridos);
				return nodo;
			} else if (compare < 0) {
				return buscarRec(dato, nodo.getIzq(), recorridos++);
			} else {
				return buscarRec(dato, nodo.getDer(), recorridos++);
			}
		}
	}

	public boolean pertenece(String dato) {
		return perteneceRec(dato, raiz);
	}

	private boolean perteneceRec(String dato, NodoABB nodo) {
		if (nodo == null)
			return false;
		else {
			int compare = dato.compareTo(nodo.getDato().getCedula());
			if (compare == 0) {
				return true;
			} else if (compare < 0) {
				return perteneceRec(dato, nodo.getIzq());
			} else {
				return perteneceRec(dato, nodo.getDer());
			}
		}
	}

	public void listarAscendente() {
		listarAscendenteRec(raiz);
		System.out.println();
	}

	private void listarAscendenteRec(NodoABB nodo) {
		if (nodo != null) {
			listarAscendenteRec(nodo.getIzq());
			System.out.println(nodo.getDato().getCedula());
			listarAscendenteRec(nodo.getDer());
		}
	}

	public void listarDescendente() {
		listarDescendenteRec(raiz);
		System.out.println();
	}

	private void listarDescendenteRec(NodoABB nodo) {
		if (nodo != null) {
			listarDescendenteRec(nodo.getDer());
			System.out.println(nodo.getDato());
			listarDescendenteRec(nodo.getIzq());
		}
	}

	public void insertar(Afiliado nuevo) {
		if (raiz == null) {
			raiz = new NodoABB(nuevo);
		} else {
			insertarRec(nuevo, raiz);
		}
	}

	private void insertarRec(Afiliado dato, NodoABB nodo) {
		int compare = dato.getCedula().compareTo(nodo.getDato().getCedula());
		if (compare < 0) {
			if (nodo.getIzq() == null) {
				nodo.setIzq(new NodoABB(dato));
			} else {
				insertarRec(dato, nodo.getIzq());
			}
		} else if (compare > 0) {
			if (nodo.getDer() == null) {
				nodo.setDer(new NodoABB(dato));
			} else {
				insertarRec(dato, nodo.getDer());
			}
		}
	}

	// Pre: !EsVacio()
	public Afiliado borrarMinimo() {
		if (raiz.getIzq() == null) {
			Afiliado ret = raiz.getDato();
			raiz = raiz.getDer();
			return ret;
		} else {
			return borrarMinimoRec(raiz);
		}
	}

	private Afiliado borrarMinimoRec(NodoABB nodo) {
		if (nodo.getIzq().getIzq() == null) {
			Afiliado ret = nodo.getIzq().getDato();
			nodo.setIzq(nodo.getIzq().getDer());
			return ret;
		} else {
			return borrarMinimoRec(nodo.getIzq());
		}
	}

	// Pre: Pertenece(dato)
	public void borrar(Afiliado dato) {
		if (raiz.getDato().getCedula().compareTo(dato.getCedula()) == 0) {
			if (raiz.getIzq() == null && raiz.getDer() == null) { // Caso 1:
																	// F�cil
				raiz = null;
			} else if (raiz.getIzq() == null) { // Caso 2 (izq null): Maso
				raiz = raiz.getDer();
			} else if (raiz.getDer() == null) { // Caso 2 (der null): Maso
				raiz = raiz.getIzq();
			} else { // Caso 3
				if (raiz.getDer().getIzq() != null) {
					raiz.setDato(borrarMinimoRec(raiz.getDer()));
				} else {
					raiz.setDato(raiz.getDer().getDato());
					raiz.setDer(raiz.getDer().getDer());
				}
			}
		} else {
			borrarRec(dato, raiz);
		}
	}

	private void borrarRec(Afiliado dato, NodoABB nodo) {
		int compare = dato.getCedula().compareTo(nodo.getDato().getCedula());
		if (compare < 0) {
			if (nodo.getIzq().getDato() == dato) {
				if (nodo.getIzq().getIzq() == null && nodo.getIzq().getDer() == null) { // Caso
																						// 1:
																						// F�cil
					nodo.setIzq(null);
				} else if (nodo.getIzq().getIzq() == null) { // Caso 2 (izq
																// null): Maso
					nodo.setIzq(nodo.getIzq().getDer());
				} else if (nodo.getIzq().getDer() == null) { // Caso 2 (der
																// null): Maso
					nodo.setIzq(nodo.getIzq().getIzq());
				} else { // Caso 3
					if (nodo.getIzq().getDer().getIzq() != null) {
						nodo.getIzq().setDato(borrarMinimoRec(nodo.getIzq().getDer()));
					} else {
						nodo.getIzq().setDato(nodo.getIzq().getDer().getDato());
						nodo.getIzq().setDer(nodo.getIzq().getDer().getDer());
					}
				}
			} else {
				borrarRec(dato, nodo.getIzq());
			}
		} else if (compare > 0) {
			if (nodo.getDer().getDato() == dato) {
				if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() == null) { // Caso
																						// 1:
																						// F�cil
					nodo.setDer(null);
				} else if (nodo.getDer().getIzq() == null) { // Caso 2 (izq
																// null): Maso
					nodo.setDer(nodo.getDer().getDer());
				} else if (nodo.getDer().getDer() == null) { // Caso 2 (der
																// null): Maso
					nodo.setDer(nodo.getDer().getIzq());
				} else { // Caso 3
					if (nodo.getDer().getDer().getIzq() != null) {
						nodo.getDer().setDato(borrarMinimoRec(nodo.getDer().getDer()));
					} else {
						nodo.getDer().setDato(nodo.getDer().getDer().getDato());
						nodo.getDer().setDer(nodo.getDer().getDer().getDer());
					}
				}
			} else {
				borrarRec(dato, nodo.getDer());
			}
		}
	}
}
