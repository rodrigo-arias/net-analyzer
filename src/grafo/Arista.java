package grafo;

public class Arista {

	// ================== Attributes =================//
	private boolean existe;
	private int valor;

	// ================== Construct ==================//
	public Arista() {
	}

	public Arista(int valor) {
		this.existe = true;
		this.valor = valor;
	}

	// ================== Properties =================//
	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor + "";
	}
}
