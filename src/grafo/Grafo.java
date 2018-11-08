package grafo;

import java.awt.Desktop;
import java.net.URL;

import cola.Cola;
import dominio.Canalera;
import dominio.Nodo;
import dominio.Servidor;

public class Grafo {

	// ================== Attributes =================//
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

	// ==================== Grafo ===================//
	public boolean isFull() {
		return cantidad == tope;
	}

	// =================== Vertice ==================//

	// Pre: !isVertice(ver) && !isFull()
	public void addVertice(Punto ver) {
		int pos = getEmptyVertice();
		vertices[pos] = ver;
		cantidad++;
	}

	public Punto getVertice(double coordX, double coordY) {
		int pos = indexVertice(coordX, coordY);
		return (pos != -1) ? vertices[pos] : null;
	}

	public Punto getVertice(int pos) {
		return vertices[pos];
	}

	public boolean isVertice(Punto ver) {
		return indexVertice(ver) != -1;
	}

	// Pre: isVertice(ver)
	public void deleteVertice(Punto ver) {
		int pos = indexVertice(ver);

		vertices[pos] = null;

		for (int i = 0; i < tope; i++) {
			matAdy[pos][i].setExiste(false);
			matAdy[i][pos].setExiste(false);
		}
	}

	private int indexVertice(Punto ver) {
		for (int i = 0; i < tope; i++)
			if (ver.equals(vertices[i]))
				return i;
		return -1;
	}

	private int indexVertice(Double coordX, Double coordY) {
		for (int i = 0; i < tope; i++)
			if (vertices[i] != null && vertices[i].getCoordX() == coordX && vertices[i].getCoordY() == coordY)
				return i;
		return -1;
	}

	private int getBusyVertice() {
		for (int i = 0; i < tope; i++)
			if (vertices[i] != null)
				return i;
		return -1;
	}

	private int getEmptyVertice() {
		for (int i = 0; i < tope; i++)
			if (vertices[i] == null)
				return i;
		return -1;
	}

	// =================== Arista ===================//

	// Pre: isVertice(origen) && isVertice(destino) &&
	// !isArista(origen, destino)
	public void addArista(Punto origen, Punto destino, int peso) {
		int posOrigen = indexVertice(origen);
		int posDestino = indexVertice(destino);

		matAdy[posOrigen][posDestino].setExiste(true);
		matAdy[posOrigen][posDestino].setValor(peso);
	}

	public void updateArista(Double origenX, Double origenY, Double destinoX, Double destinoY, int peso) {
		int posOrigen = indexVertice(origenX, origenY);
		int posDestino = indexVertice(destinoX, destinoY);

		if (matAdy[posOrigen][posDestino].isExiste()) {
			matAdy[posOrigen][posDestino].setValor(peso);
		}
	}

	public boolean isArista(Punto origen, Punto destino) {
		int posOrigen = indexVertice(origen);
		int posDestino = indexVertice(destino);

		return matAdy[posOrigen][posDestino].isExiste();
	}

	public void deleteArista(Punto origen, Punto destino) {
		int posOrigen = indexVertice(origen);
		int posDestino = indexVertice(destino);

		matAdy[posOrigen][posDestino].setExiste(false);
		matAdy[posDestino][posOrigen].setExiste(false);
	}

	// ================== Recorrido =================//

	public void DFS() {
		boolean[] vis = new boolean[tope];
		int pos = getBusyVertice();
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

		for (int i = 0; i < tope; i++) {
			if (!vis[i] && matAdy[pos][i].isExiste()) {
				DFSRec(i, vis);
			}
		}
	}

	public void BFS() {
		boolean[] vis = new boolean[tope];
		int pos = getBusyVertice();
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

	// ==================== Nodos ===================//

	public String nodoCritico() {
		String nodos = "";

		for (int i = 0; i < tope; i++) {
			boolean[] vis = new boolean[tope];

			if (vertices[i] != null && vertices[i] instanceof Nodo) {
				vis[i] = true;
				DFSRec(0, vis);
				boolean critico = false;

				for (int j = 0; j < vis.length && !critico; j++) {

					if (vertices[j] != null && vertices[j] instanceof Nodo && !vis[j]) {
						critico = true;
						nodos += vertices[i].toString() + "|";
					}
				}
			}
		}

		if (!nodos.isEmpty()) {
			nodos = nodos.substring(0, nodos.length() - 1);
		}
		return nodos;
	}

	// =================== Dijkstra =================//

	public int dijkstra(Punto origen, Punto destino) {
		int posO = indexVertice(origen);
		int posD = indexVertice(destino);

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
				if (!vis[i] && dist[i] < min) {
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

	// =================== Auxiliar =================//

	String repeatString(String s, int n) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			builder.append(s);
		}
		return builder.toString();
	}

	public void dibujarMapa() {

		String url = "http://maps.googleapis.com/maps/api/staticmap?center=" + vertices[0].toString(",") + "&zoom=15&size=1200x600&maptype=roadmap&";
		String color = "";
		String decano = "DECANO";
		String cientoveinteanosdeverdad = repeatString(decano, Math.round(tope / decano.length()) + 1);

		for (int i = 0; i < tope; i++) {
			if (vertices[i] != null) {

				if (vertices[i] instanceof Servidor) {
					color = "red";
				} else if (vertices[i] instanceof Canalera) {
					color = "blue";
				} else {
					color = "white";
				}

				url += "markers=color:" + color + "%7Clabel:" + cientoveinteanosdeverdad.charAt(i) + "%7C" + vertices[i].toString(",") + "&";
			}
		}

		url += "sensor=false&key=AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw";

		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
