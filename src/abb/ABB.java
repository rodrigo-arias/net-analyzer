package abb;

public class ABB {
	
	private NodoABB raiz;

	//==================  Construct  ==================//
	public ABB() {
		this.raiz = null;
	}

	public ABB(NodoABB raiz) {
		this.raiz = raiz;
	}

	//==================  Properties  =================//
	public NodoABB getRaiz() {
		return raiz;
	}

	//===================  Methods  ==================//
	public boolean pertenece(int dato) {
		return perteneceRec(dato, raiz);
	}

	private boolean perteneceRec(int dato, NodoABB nodo) {
		if (nodo == null)
			return false;
		else {
			if (dato == nodo.getDato()) {
				return true;
			} else if (dato < nodo.getDato()) {
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
			System.out.println(nodo.getDato());
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

	public void insertar(int dato, Object objectoDatos) {
		if (raiz == null) {
			raiz = new NodoABB(dato, objectoDatos);
		} else {
			insertarRec(dato, objectoDatos, raiz);
		}
	}

	private void insertarRec(int dato, Object objectoDatos, NodoABB nodo) {
		if (dato < nodo.getDato()) {
			if (nodo.getIzq() == null) {
				nodo.setIzq(new NodoABB(dato, objectoDatos));
			} else {
				insertarRec(dato, objectoDatos, nodo.getIzq());
			}
		} else if (dato > nodo.getDato()) {
			if (nodo.getDer() == null) {
				nodo.setDer(new NodoABB(dato, objectoDatos));
			} else {
				insertarRec(dato, objectoDatos, nodo.getDer());
			}
		}
	}

	// Pre: !EsVacio()
	public int borrarMinimo() {
		if (raiz.getIzq() == null) {
			int ret = raiz.getDato();
			raiz = raiz.getDer();
			return ret;
		} else {
			return borrarMinimoRec(raiz);
		}
	}

	private int borrarMinimoRec(NodoABB nodo) {
		if (nodo.getIzq().getIzq() == null) {
			int ret = nodo.getIzq().getDato();
			nodo.setIzq(nodo.getIzq().getDer());
			return ret;
		} else {
			return borrarMinimoRec(nodo.getIzq());
		}
	}

	// Pre: Pertenece(dato)
	public void borrar(int dato) {
		if (raiz.getDato() == dato) {
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

	private void borrarRec(int dato, NodoABB nodo) {
		if (dato < nodo.getDato()) {
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
		} else if (dato > nodo.getDato()) {
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
