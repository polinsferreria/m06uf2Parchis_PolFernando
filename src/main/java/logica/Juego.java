/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Casillas;
import modelo.Dado;
import modelo.Fitxes;
import modelo.Jugador;

/**
 *
 * @author usuario
 */
public class Juego {

	private static Scanner sc = new Scanner(System.in);

	private static Casillas[] tablero = Casillas.tablero();

	private static Fitxes[] fitxesPartida;

	public static Jugador[] orden(ArrayList<Jugador> jugadores) {

		ArrayList<Integer> resultados = new ArrayList<>();

		// Lanzar los dados para cada jugador y almacenar los resultados
		for (Jugador jugador : jugadores) {
			System.out.println("El jugador " + jugador.getNom() + " tira los dados...");
			Dado dado = new Dado();
			ArrayList<Integer> res = dado.getResultado();
			int sumaDados = res.get(0) + res.get(1);
			resultados.add(sumaDados);
			System.out.println("Ha sacado un " + res.get(0) + " y un " + res.get(1));
		}

		// Crear una copia de los resultados para ordenarlos
		ArrayList<Integer> resultadosOrdenados = new ArrayList<>(resultados);
		Collections.sort(resultadosOrdenados, Collections.reverseOrder());

		// Crear un arreglo de jugadores ordenados según los resultados de lanzamiento
		// de dados
		Jugador[] ordenados = new Jugador[jugadores.size()];
		for (int i = 0; i < resultadosOrdenados.size(); i++) {
			int resultado = resultadosOrdenados.get(i);
			int indiceJugador = resultados.indexOf(resultado);
			ordenados[i] = jugadores.get(indiceJugador);
			resultados.set(indiceJugador, null); // Marcamos el resultado como procesado
		}
		inicializarfitxas(ordenados);
		return ordenados;

	}

	private static void inicializarfitxas(Jugador[] jugadoresOrdenados) {// hacemos un arraylist con todas las fichas de
																			// los jugadores
		for (int i = 0; i <= jugadoresOrdenados.length
				- 1/* se podria poner el array de jugadores FERB; nada ya esta :D */; i++) {
			for (int j = 0; j < jugadoresOrdenados[i].getFitxes().size() - 1; j++) {
				Juego.fitxesPartida[i] = jugadoresOrdenados[i].getFitxes().get(j);
			}
		}

	}
	// public static ArrayList<Fitxes> fitxesPartida = new ArrayList<Fitxes>() ; en
	// otra clase -- en la clase partida creo

	public static ArrayList<Integer> tirarDado(Jugador jugador, int cont) {

		System.out.println("xl Jugadore" + jugador.getNom() + " tira Dados...");
		Juego.sc.nextLine();

		Dado d = new Dado();
		ArrayList<Integer> resultado = d.getResultado();

		System.out.println("Ha sacado un " + resultado.get(0) + " y un " + resultado.get(1));

		if (resultado.get(0) == resultado.get(1) && cont <= 3) {

			System.out.println("Felices salieron dobles");
			if (comprovarFichas(jugador)) {
				if (!Sacarficha(jugador)) {
					moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
				}
			} else {
				moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
			}

			tirarDado(jugador, cont++);

			return null;

		} else if (resultado.get(0) == resultado.get(1)) {

			System.out.println("Lastima perdiste una ficha... ");

			elegirFichaEliminar(jugador);
		}

		moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
		// cambioTurno;
		return resultado;

	}

//metodo de fitxas de cada jugador al array fitxesPartida
	public static boolean Sacarficha(Jugador jugador) {

		System.out.println("Quieres sacar una ficha? [Si / No]");

		do {

			String res = sc.nextLine();

			if (res.equals("Si")) {
				for (Fitxes fitxa : jugador.getFitxes()) {
					if (!fitxa.isActiva()) {
						fitxa.setPosicio(getCasillaSalida(jugador));
						fitxa.setActiva(true);
						return true;

					}
				}

			} else if (res.equals("No")) {

				return false;

			}

		} while (true);

	}

	private static int getCasillaSalida(Jugador jugador) {
		for (int i = 0; i < Casillas.CASILLAS_SALIDA.length; i++) {
			if (tablero[Casillas.CASILLAS_SALIDA[i]].getTipoCasilla().endsWith(jugador.getColor())) {
				return Casillas.CASILLAS_SALIDA[i];

			}
		}
		return 0;
	}

	public static int getCasillaEntrada(Jugador jugador) {
		int CE = getCasillaSalida(jugador) - 5;
		return CE == 0 ? 68 : CE;

	}

	public static void moverFitxa(Jugador jugador, int numAvances, Casillas[] tablero) {
		Fitxes f = elegirFicha(jugador);
		desbloquearCasilla(f, tablero);
		for (int i = 1; i <= numAvances; i++) {

			if (tablero[(f.getPosicio() + 1) % 67 + 1].getBloqueado() == Casillas.KEY_BLOQUEADO) {
				break;
			} else {
				if (tablero[(f.getPosicio() + 1) % 67 + 1].getPosicion() == getCasillaEntrada(jugador)) {
					// crear tablero casa
				}
				f.setPosicio(f.getPosicio() + 1);
			}

		}
		if (casillaSegura(f, tablero)) {// casilla segura

			if (hayficha(f.getPosicio())/* , Juego.fitxesPartida[] */) {// casilla segura con una ficha = a bloqueo
				tablero[f.getPosicio()].setBloqueado(Casillas.KEY_BLOQUEADO);

			}
			return;
		} else {
			if (hayfichaColor(f.getPosicio(), jugador)) {
				tablero[f.getPosicio()].setBloqueado(Casillas.KEY_BLOQUEADO);
			}
		} // si hay una ficha de un mismo color en una casilla no salvens pues se bloquea
			// tambien; asi q else{hayFichaColor()}

	}

	private static boolean hayfichaColor(int posi, Jugador jugador) {
		for (Fitxes fitxa : jugador.getFitxes()) {
			if (posi == fitxa.getPosicio()) {
				return true;
			}
		}
		return false;
	}

	private static boolean hayficha(int posi/* , ArrayList<Jugador> fitxas */) {
		for (int i = 0; i < Juego.fitxesPartida.length; i++) {
			if (posi == fitxesPartida[i].getPosicio()) {
				return true;
			}
		}
		return false;
	}

	private static boolean casillaSegura(Fitxes f, Casillas[] tablero) {
		return tablero[f.getPosicio()].getTipoCasilla().equals(Casillas.STR_TIPO_SEGURA);
	}

	private static boolean desbloquearCasilla(Fitxes f, Casillas[] tablero) {
		boolean Debloqueado = false;
		if (tablero[f.getPosicio()].getBloqueado() == Casillas.KEY_BLOQUEADO) {
			tablero[f.getPosicio()].setBloqueado(Casillas.KEY_NO_BLOQUEADO);
			Debloqueado = true;
		}
		return Debloqueado;

	}

	private static boolean comprovarFichas(Jugador jugador) {

		ArrayList<Fitxes> fitxes = (ArrayList<Fitxes>) jugador.getFitxes();

		if (tablero[getCasillaSalida(jugador)].getBloqueado() == Casillas.KEY_BLOQUEADO) {

			return false;
		}

		for (Fitxes fitxe : fitxes) {

			if (!fitxe.isActiva()) {

				return true;

			}

		}

		return false;

	}

	private static Fitxes elegirFicha(Jugador jugador) {

		String fichasActivas = "";
		int contador = 0;

		ArrayList<Fitxes> fitxes = jugador.getFitxes();

		for (Fitxes ficha : fitxes) {
			if (ficha.isActiva()) {
				contador++;
				fichasActivas += contador + " [" + ficha.getPosicio() + "] ";
			}
		}

		System.out.println("Fichas activas: " + fichasActivas);

		if (contador == 0) {
			System.out.println("No hay fichas activas para eliminar.");
			return null; // No hay fichas activas, salir del m�todo
		}

		int opcion;
		do {
			System.out.print("Seleccione el n�mero de la ficha que desea eliminar: ");
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > contador) {
					System.err.println("Error: Seleccione un n�mero v�lido.");
				} else {
					break; // Salir del bucle si la entrada es v�lida
				}
			} else {
				System.err.println("Error: Ingrese un n�mero entero.");
				sc.next(); // Limpiar el buffer de entrada
			}
		} while (true);

		int fichaSeleccionada = 0;
		for (Fitxes ficha : fitxes) {
			if (ficha.isActiva()) {
				fichaSeleccionada++;
				if (fichaSeleccionada == opcion) {
					return ficha; // Salir del m�todo despu�s de eliminar la ficha
				}
			}
		}
		return null;
	}

	public static void elegirFichaEliminar(Jugador jugador) {

		elegirFicha(jugador).setActiva(false);
		System.out.println("Ficha eliminada con �xito.");
	}

}
