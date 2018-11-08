package grafo;

import cola.Cola;
import dominio.Canalera;
import dominio.Nodo;
import dominio.Servidor;

public class Grafo {

	private Punto[] vertices;
	private Arista[][] matAdy;
	private int tope;
	private int cantidad;

	// ================== Construct ==================//
	public Grafo(int tope) {
		this.tope = tope;
		this.vertices = new Punto[tope];
		this.matAdy = new Arista[tope][tope];

		for (int i = 0; i < tope; i++) {
			for (int j = i; j < tope; j++) {
				matAdy[i][j] = matAdy[j][i] = new Arista();
			}
		}
	}

	// =================== Methods ==================//

	public Punto obtenerServidor() {
		for (int i = 0; i < tope; i++)
			if (vertices[i] instanceof Servidor)
				return vertices[i];
		return null;
	}

	private int posOcupada() {
		for (int i = 0; i < tope; i++)
			if (vertices[i] != null)
				return i;
		return -1;
	}

	private int posLibre() {
		for (int i = 0; i < tope; i++)
			if (vertices[i] == null)
				return i;
		return -1;
	}

	// Pre: !existeVertice(ver) && !esLleno()
	public void agregarVertice(Punto ver) {
		int pos = posLibre();
		vertices[pos] = ver;
		cantidad++;
	}

	// Pre: existeVertice(origen) && existeVertice(destino) &&
	// !existeArista(origen, destino)
	public void agregarArista(Punto origen, Punto destino, int peso) {
		int posOrigen = posVertice(origen);
		int posDestino = posVertice(destino);

		matAdy[posOrigen][posDestino].setExiste(true);
		matAdy[posOrigen][posDestino].setValor(peso);
	}

	public void modificarArista(Double origenX, Double origenY, Double destinoX, Double destinoY, int peso) {
		int posOrigen = posVerticeCoord(origenX, origenY);
		int posDestino = posVerticeCoord(destinoX, destinoY);

		if (matAdy[posOrigen][posDestino].isExiste()) {
			matAdy[posOrigen][posDestino].setValor(peso);
		}
	}

	public boolean esLleno() {
		return cantidad == tope;
	}

	private int posVertice(Punto ver) {
		for (int i = 0; i < tope; i++)
			if (ver.equals(vertices[i]))
				return i;
		return -1;
	}

	private int posVerticeCoord(Double coordX, Double coordY) {
		for (int i = 0; i < tope; i++)
			if (vertices[i] != null && vertices[i].getCoordX() == coordX && vertices[i].getCoordY() == coordY)
				return i;
		return -1;
	}

	public boolean existeVertice(Punto ver) {
		return posVertice(ver) != -1;
	}

	public Punto obtenerVertice(double coordX, double coordY) {

		int pos = posVerticeCoord(coordX, coordY);

		return (pos != -1) ? vertices[pos] : null;
	}

	public boolean existeArista(Punto origen, Punto destino) {
		int posOrigen = posVertice(origen);
		int posDestino = posVertice(destino);

		return matAdy[posOrigen][posDestino].isExiste();
	}

	// Pre: existeVertice(ver)
	public void borrarVertice(Punto ver) {
		int pos = posVertice(ver);

		vertices[pos] = null;

		for (int i = 0; i < tope; i++) {
			matAdy[pos][i].setExiste(false);
			matAdy[i][pos].setExiste(false);
		}
	}

	public void borrarArista(Punto origen, Punto destino) {
		int posOrigen = posVertice(origen);
		int posDestino = posVertice(destino);

		matAdy[posOrigen][posDestino].setExiste(false);
		matAdy[posDestino][posOrigen].setExiste(false);
	}

	public void DFS() {
		boolean[] vis = new boolean[tope];
		int pos = posOcupada();
		if (pos != -1) {
			for (int i = 0; i < tope; i++) {
				if (!vis[i] && vertices[i] != null) {
					DFSRec(i, vis);
				}
			}
		}
	}

	private void DFSRec(int pos, boolean[] vis) {
		vis[pos] = true;
		// System.out.println(vertices[pos]);
		for (int i = 0; i < tope; i++) {
			if (!vis[i] && matAdy[pos][i].isExiste()) {
				DFSRec(i, vis);
			}
		}
	}

	public void BFS() {
		boolean[] vis = new boolean[tope];
		int pos = posOcupada();
		if (pos != -1) {
			for (int i = 0; i < tope; i++) {
				if (!vis[i] && vertices[i] != null) {
					BFSRec(i, vis);
				}
			}
		}
	}

	private void BFSRec(int pos, boolean[] vis) {
		Cola<Integer> cola = new Cola<Integer>();
		vis[pos] = true;
		cola.encolar(pos);
		while (!cola.esVacia()) {
			int posNueva = cola.desencolar();
			System.out.println(vertices[posNueva]);
			for (int i = 0; i < tope; i++) {
				if (!vis[i] && matAdy[posNueva][i].isExiste()) {
					vis[i] = true;
					cola.encolar(i);
				}
			}
		}
	}

	public int dijkstra(Punto origen, Punto destino) {
		int posO = posVertice(origen);
		int posD = posVertice(destino);

		int[] dist = new int[tope];
		int[] ant = new int[tope];
		boolean[] vis = new boolean[tope];

		for (int i = 0; i < tope; i++) {
			ant[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}

		dijkstraInterno(posO, dist, ant, vis);

		return dist[posD];
	}

	private void dijkstraInterno(int posO, int[] dist, int[] ant, boolean[] vis) {
		dist[posO] = 0;
		vis[posO] = true;

		for (int i = 0; i < tope; i++) {
			if (matAdy[posO][i].isExiste()) {
				dist[i] = matAdy[posO][i].getValor();
				ant[i] = posO;
			}
		}

		for (int k = 1; k < tope; k++) {
			int posCand = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < tope; i++) {
				if (!vis[i] && dist[i] < min && (this.vertices[i] instanceof Canalera)) {
					min = dist[i];
					posCand = i;
				}
			}

			if (posCand == -1)
				return;

			vis[posCand] = true;

			for (int i = 0; i < tope; i++) {
				if (!vis[i] && matAdy[posCand][i].isExiste()) {
					int sumaDist = dist[posCand] + matAdy[posCand][i].getValor();
					if (sumaDist < dist[i]) {
						dist[i] = sumaDist;
						ant[i] = posCand;
					}
				}
			}
		}
	}

}
