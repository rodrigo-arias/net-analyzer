package sistema;


public class Retorno {

    public enum Resultado {OK, ERROR_1, ERROR_2, ERROR_3,     
         ERROR_4, ERROR_5, NO_IMPLEMENTADA};
 
    public int valorEntero;

    public String valorString;
    
    public Resultado resultado;
    
    public Retorno() {
		this.resultado = Resultado.NO_IMPLEMENTADA;
	}
	
	public Retorno(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public Retorno(Resultado resultado, String valorstr, int valorEnt) {
		this.resultado = resultado;
		this.valorEntero = valorEnt;
		this.valorString = valorstr;
	}

}
