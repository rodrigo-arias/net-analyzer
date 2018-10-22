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
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarAfiliado() {
		fail("Not yet implemented");
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
