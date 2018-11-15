package grafo;

public class Arista {

	// ================== Attributes =================//
	private boolean existe;
	private int valor;
	private boolean habilitado;

	// ================== Construct ==================//
	public Arista() {
	}

	public Arista(int valor) {
		this.existe = true;
		this.valor = valor;
		this.habilitado = true;
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
	
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}	

	@Override
	public String toString() {
		return valor + "";
	}
}
