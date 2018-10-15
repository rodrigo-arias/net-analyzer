package sistema;

public class Main {
	
	public static void main(String[] args) {
		
		// Pruebas de desarrollo
		System.out.println("Pruebas de desarrollo");
		
		Sistema s = new Sistema();
	
		// Funciones
		s.inicializarSistema(10, 1.0, 1.0);
		
		s.red.DFS();
		s.red.BFS();
	}
}
