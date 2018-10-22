package sistema;

public class Main {
	
	public static void main(String[] args) {
		
		// Pruebas de desarrollo
		System.out.println("Pruebas de desarrollo");
		
		Sistema s = new Sistema();
	
		// Funciones
		s.inicializarSistema(10, 1.0, 1.0);
		
		//Retorno nuevoAfiliado = s.registrarAfiliado("5.103.782-1", "Nicolas", "nhg1612@gmail.com");
		
		s.red.DFS();
		s.red.BFS();
	}
}
