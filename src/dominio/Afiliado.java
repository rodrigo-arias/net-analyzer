package dominio;

public class Afiliado {

	private String cedula;
	private String nombre;
	private String email;
	
	//==================  Construct  ==================//
	public Afiliado(String cedula, String nombre, String email) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.email = email;
	}
	
	//==================  Properties  =================//
	public String getCedula() {
        return cedula;
    }
	
	public String getNombre() {
        return nombre;
    }
	
	public String getEmail() {
        return email;
    }
	
	public void setCedula(String cedula) {
        this.cedula = cedula;
    }
	
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
	public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return getCedula() + ";" + getNombre() + ";" + getEmail();
	}
	
}
