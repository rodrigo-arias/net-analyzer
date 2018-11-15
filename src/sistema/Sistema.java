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

			red = new Grafo(maxPuntos);
			afiliados = new ABB();

			Servidor servidor = new Servidor(coordX, coordY);
			red.addVertice(servidor);

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
			NodoABB afiliado = afiliados.buscar(CI);

			if (afiliado != null) {
				return new Retorno(Resultado.OK, afiliado.toString(), afiliado.getRecorridos());
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno listarAfiliados() {
		return new Retorno(Resultado.OK, afiliados.listarAscendente(), 0);
	}

	@Override
	public Retorno registrarCanalera(String chipid, String CIafiliado, Double coordX, Double coordY) {

		if (!red.isFull()) {

			if (red.getVertice(coordX, coordY) == null) {

				if (buscarAfiliado(CIafiliado).resultado == Resultado.OK) {

					Canalera canalera = new Canalera(coordX, coordY, chipid, CIafiliado);

					red.addVertice(canalera);

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

		if (!red.isFull()) {

			if (red.getVertice(coordX, coordY) == null) {

				Nodo nodo = new Nodo(coordX, coordY, nodoid);

				red.addVertice(nodo);

				return new Retorno(Resultado.OK);

			} else
				return new Retorno(Resultado.ERROR_2);

		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int perdidaCalidad) {

		if (perdidaCalidad >= 1) {
			Punto origen = red.getVertice(coordXi, coordYi);
			Punto destino = red.getVertice(coordXf, coordYf);

			if (origen != null && destino != null) {
				if (!red.isArista(origen, destino)) {

					if (origen instanceof Servidor && destino instanceof Canalera
							|| origen instanceof Canalera && destino instanceof Servidor) {

						return new Retorno(Resultado.ERROR_4);
					} else {
						red.addArista(origen, destino, perdidaCalidad);
						return new Retorno(Resultado.OK);
					}
				} else
					return new Retorno(Resultado.ERROR_3);
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno modificarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf,
			int nuevoValorPerdidaCalidad) {

		if (nuevoValorPerdidaCalidad >= 1) {

			Punto origen = red.getVertice(coordXi, coordYi);
			Punto destino = red.getVertice(coordXf, coordYf);

			if (origen != null && destino != null && red.isArista(origen, destino)) {

				red.updateArista(coordXi, coordYi, coordXf, coordYf, nuevoValorPerdidaCalidad);
				return new Retorno(Resultado.OK);

			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno calidadCanalera(Double coordX, Double coordY) {

		Punto destino = red.getVertice(coordX, coordY);
		Punto origen = red.getVertice(0);

		if (destino != null && destino instanceof Canalera) {
			int camino = red.dijkstra(origen, destino);

			if (camino > 0) {
				return new Retorno(Resultado.OK, "", camino);
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno nodosCriticos() {
		return new Retorno(Resultado.OK, red.nodoCritico(), 0);
	}

	@Override
	public Retorno dibujarMapa() {
		red.dibujarMapa();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno deshabilitarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {

		Punto origen = red.getVertice(coordXi, coordYi);
		Punto destino = red.getVertice(coordXf, coordYf);

		if (origen != null && destino != null) {

			if (red.isArista(origen, destino)) {

				if (red.isEnabledArista(origen, destino)) {
					red.disableArista(origen, destino);
					return new Retorno(Resultado.OK);
				} else
					return new Retorno(Resultado.ERROR_3);
			} else
				return new Retorno(Resultado.ERROR_2);
		} else
			return new Retorno(Resultado.ERROR_1);
	}
}
