package sistema;

import abb.ABB;
import abb.NodoABB;
import dominio.Afiliado;
import dominio.Canalera;
import dominio.Nodo;
import dominio.Servidor;
import grafo.Grafo;
import grafo.Punto;
import sistema.Retorno.Resultado;

public class Sistema implements ISistema {

	public Grafo red = null;
	private ABB afiliados = null;

	@Override
	public Retorno inicializarSistema(int maxPuntos, Double coordX, Double coordY) {

		if (maxPuntos <= 0) {
			return new Retorno(Resultado.ERROR_1);
		} else {

			this.red = new Grafo(maxPuntos);
			this.afiliados = new ABB();
			Punto servidor = new Punto(coordX, coordY, new Servidor());
			this.red.agregarVertice(servidor);

			return new Retorno(Resultado.OK);
		}
	}

	@Override
	public Retorno destruirSistema() {

		this.red = null;
		this.afiliados = null;

		System.gc();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarAfiliado(String cedula, String nombre, String email) {

		if (Utilidades.ValidarCI(cedula)) {

			if (Utilidades.ValidarEmail(email)) {

				// No existe un afiliado registrado con esa CI
				if (buscarAfiliado(cedula).resultado == Resultado.ERROR_2) {

					Afiliado nuevo = new Afiliado(cedula, nombre, email);

					afiliados.insertar(nuevo);

					return new Retorno(Resultado.OK);
				} else {
					return new Retorno(Resultado.ERROR_3);
				}
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno buscarAfiliado(String CI) {

		if (Utilidades.ValidarCI(CI)) {
			NodoABB afiliado = this.afiliados.buscar(CI);

			if (afiliado != null) {
				return new Retorno(Resultado.OK, afiliado.toString(), afiliado.getRecorridos());
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno listarAfiliados() {
		afiliados.listarAscendente();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarCanalera(String chipid, String CIafiliado, Double coordX, Double coordY) {

		if (!red.esLleno()) {

			if (red.obtenerVertice(coordX, coordY) != null) {

				if (buscarAfiliado(CIafiliado).resultado != Resultado.OK) {

					Canalera canalera = new Canalera(chipid, CIafiliado);
					Punto nuevo = new Punto(coordX, coordY, canalera);

					red.agregarVertice(nuevo);

					return new Retorno(Resultado.OK);
				} else
					return new Retorno(Resultado.ERROR_3);

			} else
				return new Retorno(Resultado.ERROR_2);

		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno registrarNodo(String nodoid, Double coordX, Double coordY) {

		if (!red.esLleno()) {

			if (red.obtenerVertice(coordX, coordY) != null) {

				Nodo nodo = new Nodo(nodoid);
				Punto nuevo = new Punto(coordX, coordY, nodo);

				red.agregarVertice(nuevo);

				return new Retorno(Resultado.OK);

			} else
				return new Retorno(Resultado.ERROR_2);

		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int perdidaCalidad) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno modificarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf,
			int nuevoValorPerdidaCalidad) {

		if (nuevoValorPerdidaCalidad >= 1) {

			Punto origen = red.obtenerVertice(coordXi, coordYi);
			Punto destino = red.obtenerVertice(coordXf, coordYf);

			if (origen != null && destino != null && red.existeArista(origen, destino)) {

				red.modificarArista(coordXi, coordYi, coordXf, coordYf, nuevoValorPerdidaCalidad);

				return new Retorno(Resultado.OK);

			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno calidadCanalera(Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno nodosCriticos() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

}
