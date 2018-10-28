package grafo;

public class Punto {

	private double coordX;
	private double coordY;
	private Object element;

	//==================  Construct  ==================//
	public Punto(double coordX, double coordY, Object element) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.element = element;
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

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
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
