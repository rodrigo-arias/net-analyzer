package grafo;

import dominio.Nodo;

public class Punto<T> {

	private double coordX;
	private double coordY;
	private T elemento;

	//==================  Construct  ==================//
	public Punto(double coordX, double coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	//==================  Properties  =================//
	public double getCoordX() {
		return coordX;
	}

	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	
	public <T> T getElemento() {
		return (T) elemento;
	}

	public void setNodo(T elemento) {
		this.elemento = elemento;
	}

	//===================  Methods  ==================//
	@Override
	public String toString() {
		return "[" + coordX + ", " + coordY + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coordX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(coordY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		if (Double.doubleToLongBits(coordX) != Double.doubleToLongBits(other.coordX))
			return false;
		if (Double.doubleToLongBits(coordY) != Double.doubleToLongBits(other.coordY))
			return false;
		return true;
	}
}
