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
	}

	@Test
	public void testInicializarSistema() {
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
		s.inicializarSistema(1, 1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, s.registrarAfiliado("5.103.782-1", "Nicolas", "nhg1612@gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, s.registrarAfiliado("5.103.7821", "Nicolas", "nhg1612@gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, s.registrarAfiliado("5.103.782-1", "Nicolas", "nhg1612gmail.com").resultado);
		assertEquals(Retorno.Resultado.ERROR_3, s.registrarAfiliado("5.103.782-1", "Nicolas", "nhg1612@gmail.com").resultado);
	}

	@Test
	public void testBuscarAfiliado() {
		s.inicializarSistema(1, 1.0, 1.0);
		s.registrarAfiliado("5.103.782-1", "Nicolas", "nhg1612@gmail.com");
		assertEquals(Retorno.Resultado.OK, s.buscarAfiliado("5.103.782-1").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, s.buscarAfiliado("5.103.7821").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, s.buscarAfiliado("5.111.777-8").resultado);
	}

	@Test
	public void testListarAfiliados() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistrarCanalera() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistrarNodo() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistrarTramo() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificarTramo() {
		fail("Not yet implemented");
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
