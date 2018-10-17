package sistema;

import java.util.regex.Pattern;

import abb.ABB;
import dominio.Afiliado;
import grafo.Grafo;
import grafo.Punto;
import sistema.Retorno.Resultado;

public class Sistema implements ISistema {
	
	public Grafo red = null;
	private ABB afiliados = null;

	@Override
	public Retorno inicializarSistema(int maxPuntos, Double coordX, Double coordY) {
		
		if(maxPuntos <= 0) {
			return new Retorno(Resultado.ERROR_1);
		} else {
			
			this.red = new Grafo(maxPuntos);
			Punto servidor = new Punto(coordX, coordY);
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
		Retorno ret = new Retorno();
		ret.resultado = ret.resultado.OK;
		EmailValidator validadorEmail = new EmailValidator();
		
		if(Pattern.matches("[0-9]((.)[0-9]{3}){2}(-)[0-9]$", cedula)) {
			if(validadorEmail.validate(email)) {
				Afiliado nuevoAfiliado = new Afiliado(cedula, nombre, email);
				//afiliados.insertar((int)cedula, (Object)nuevoAfiliado);
			}else
				ret.resultado = ret.resultado.ERROR_2;
		}else
			ret.resultado = ret.resultado.ERROR_1;
		
		return ret;
	}

	@Override
	public Retorno buscarAfiliado(String CI) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listarAfiliados() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarCanalera(String chipid, String CIafiliado, Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarNodo(String nodoid, Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int perdidaCalidad) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno modificarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf,
			int nuevoValorPerdidaCalidad) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
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
