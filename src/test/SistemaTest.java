package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import sistema.Retorno;
import sistema.Sistema;

public class SistemaTest {

	private Sistema s;

	@Before
	public void set() throws Exception {
		s = new Sistema();
		s.inicializarSistema(1, 1.0, 1.0);
	}

	@Test
	public void testInicializarSistema() {
		s.destruirSistema();
		assertEquals(Retorno.Resultado.OK, s.inicializarSistema(1, 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, s.inicializarSistema(0, 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, s.inicializarSistema(-1, 1.0, 1.0).resultado);
	}

	@Test
	public void testDestruirSistema() {
		assertEquals(Retorno.Resultado.OK, s.destruirSistema().resultado);
	}

	@Test
	public void testRegistrarAfiliado() {
		assertEquals(Retorno.Resultado.OK,
				s.registrarAfiliado("5.103.782-1", "Nicolas Hernandez", "nhg1612@gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_1,
				s.registrarAfiliado("5.103.7821", "Nicolas Hernandez", "nhg1612@gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_2,
				s.registrarAfiliado("5.103.782-1", "Nicolas Hernandez", "nhg1612gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_3,
				s.registrarAfiliado("5.103.782-1", "Nicolas Hernandez", "nhg1612@gmail.com").resultado);
	}

	@Test
	public void testBuscarAfiliado() {
		s.registrarAfiliado("5.103.782-1", "Nicolas Hernandez", "nhg1612@gmail.com");
		assertEquals(Retorno.Resultado.OK, s.buscarAfiliado("5.103.782-1").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, s.buscarAfiliado("5.103.7821").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, s.buscarAfiliado("5.111.777-8").resultado);
	}

	@Test
	public void testListarAfiliados() {
		s.registrarAfiliado("5.103.782-1", "Nicolas Hernandez", "nhg1612@gmail.com");
		s.registrarAfiliado("5.111.777-8", "Rodrigo Arias", "rodrigoa@gmail.com");
		s.registrarAfiliado("4.403.120-5", "Sebastían Fernandez", "sebita@nacional.com");
		assertEquals(Retorno.Resultado.OK, s.listarAfiliados().resultado);
	}

	@Test
	public void testRegistrarCanalera() {
		assertEquals(Retorno.Resultado.ERROR_1, s.registrarCanalera("ASD123", "5.111.777-8", 2.0, 3.0).resultado);

		s.destruirSistema();
		s.inicializarSistema(10, 1.0, 2.0);
		s.registrarAfiliado("5.111.777-8", "Rodrigo Arias", "rodrigoa@gmail.com");
		assertEquals(Retorno.Resultado.ERROR_2, s.registrarCanalera("ASD123", "5.111.777-8", 1.0, 2.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_3, s.registrarCanalera("ASD123", "3.455.666-2", 3.0, 4.0).resultado);
		assertEquals(Retorno.Resultado.OK, s.registrarCanalera("ASD123", "5.111.777-8", 3.0, 4.0).resultado);
	}

	@Test
	public void testRegistrarNodo() {
		assertEquals(Retorno.Resultado.ERROR_1, s.registrarNodo("N123", 2.0, 3.0).resultado);

		s.destruirSistema();
		s.inicializarSistema(10, 1.0, 2.0);
		assertEquals(Retorno.Resultado.ERROR_2, s.registrarNodo("N123", 1.0, 2.0).resultado);
		assertEquals(Retorno.Resultado.OK, s.registrarNodo("N123", 3.0, 4.0).resultado);
	}

	@Test
	public void testRegistrarTramo() {
		s.destruirSistema();
		s.inicializarSistema(10, 1.0, 2.0);
		s.registrarNodo("N123", 3.0, 4.0);
		s.registrarNodo("N235", 43.0, 2.0);

		assertEquals(Retorno.Resultado.ERROR_1, s.registrarTramo(3.0, 4.0, 43.0, 2.0, -3).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, s.registrarTramo(4.0, 4.0, 43.0, 2.0, 2).resultado);
		assertEquals(Retorno.Resultado.OK, s.registrarTramo(3.0, 4.0, 43.0, 2.0, 2).resultado);
		assertEquals(Retorno.Resultado.ERROR_3, s.registrarTramo(3.0, 4.0, 43.0, 2.0, 2).resultado);
	}

	@Test
	public void testModificarTramo() {
		s.destruirSistema();
		s.inicializarSistema(10, 1.0, 2.0);
		s.registrarNodo("N123", 3.0, 4.0);
		s.registrarNodo("N235", 43.0, 2.0);
		s.registrarTramo(3.0, 4.0, 43.0, 2.0, 2);
		
		assertEquals(Retorno.Resultado.ERROR_1, s.modificarTramo(3.0, 4.0, 43.0, 2.0, -1).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, s.modificarTramo(3.0, 4.0, 22.0, 2.0, 3).resultado);
		assertEquals(Retorno.Resultado.OK, s.modificarTramo(3.0, 4.0, 43.0, 2.0, 3).resultado);
	}

	@Test
	public void testCalidadCanalera() {
		fail("Not yet implemented");
	}

	@Test
	public void testNodosCriticos() {
		fail("Not yet implemented");
	}

	@Test
	public void testDibujarMapa() {
		fail("Not yet implemented");
	}

}
