/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import modelo.Casillas;
import modelo.Dado;
import modelo.Fitxes;
import modelo.Jugador;
import modelo.Partides;

/**
 *
 * @author usuario
 */
public class Juego {

	private Scanner sc;
	private Partides partida;
	private Casillas[] tablero;
	// private Map<String, Casillas[]> tableroscasas;

	private Fitxes[] fitxesPartida;
	private Jugador[] jugadoresOrdenados;

	public Juego(Partides partida, ArrayList<Jugador> jugadores) {
		super();
		sc = new Scanner(System.in);
		tablero = Casillas.tablero();
		this.partida = partida;
		jugadoresOrdenados = orden(jugadores);
		gestionarTurnos(jugadoresOrdenados);
	}

	private void gestionarTurnos(Jugador[] jugadoresOrdenados) {

		do {
			for (int i = 0; i < jugadoresOrdenados.length - 1; i++) {
				if (jugadoresOrdenados[i] != null) {
					tirarDado(jugadoresOrdenados[i], 0);
				}

			}
		} while (partida.isEnCurso());
	}

	private Jugador[] orden(ArrayList<Jugador> jugadores) {

		ArrayList<Integer> resultados = new ArrayList<>();

		// Lanzar los dados para cada jugador y almacenar los resultados
		for (Jugador jugador : jugadores) {
			System.out.println("El jugador " + jugador.getNom() + " tira los dados...");
			Dado dado = new Dado();
			ArrayList<Integer> res = dado.getResultado();
			int sumaDados = res.get(0) + res.get(1);
			resultados.add(sumaDados);
			System.out.println("Ha sacado un " + res.get(0) + " y un " + res.get(1));
			Jugador.inicializarFitxes(partida, jugador);
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
		inicializarfitxasArry(ordenados);
		return ordenados;

	}

	private void inicializarfitxasArry(Jugador[] jugadoresOrdenados) {// hacemos un arraylist con todas las fichas de
		// los jugadores
		for (int i = 0; i <= jugadoresOrdenados.length - 1; i++) {
			for (int j = 0; j < jugadoresOrdenados[i].getFitxes().size(); j++) {
				if (jugadoresOrdenados[i].getFitxes().get(j).getPartida().getIdPartida() == partida.getIdPartida()) {
					fitxesPartida[i] = jugadoresOrdenados[i].getFitxes().get(j);
				}
			}
		}

	}

	public void tirarDado(Jugador jugador, int cont) {// el cont es para saber cuantas veces tira

		System.out.println("xl Jugadore" + jugador.getNom() + " tira Dados...");
		sc.nextLine();

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

			tirarDado(jugador, cont++);// si tira mas de 3 veces dobles se cumplira el de abajo
			return;

		} else if (resultado.get(0) == resultado.get(1)) {// tendra q eliminar ficha

			System.out.println("Lastima perdiste una ficha... ");

			eliminarFicha(elegirFicha(jugador, "Eliminar"));
			return;
		}

		moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
		// cambioTurno;

	}

//metodo de fitxas de cada jugador al array fitxesPartida
	public boolean Sacarficha(Jugador jugador) {

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

	private int getCasillaSalida(Jugador jugador) {
		for (int i = 0; i < Casillas.CASILLAS_SALIDA.length; i++) {
			if (tablero[Casillas.CASILLAS_SALIDA[i]].getTipoCasilla().endsWith(jugador.getColor())) {
				return Casillas.CASILLAS_SALIDA[i];

			}
		}
		return 0;
	}

	public int getCasillaEntrada(Jugador jugador) {
		int CE = getCasillaSalida(jugador) - 5;
		return CE == 0 ? 68 : CE;

	}

	public void moverFitxa(Jugador jugador, int numAvances, Casillas[] tablero) {

		Fitxes f = elegirFicha(jugador, "mover");
		Fitxes f2 = null;

		desbloquearCasilla(f, tablero);

		boolean enTableroCasa = f.getPosicio() < 68;
		boolean retroceso = false;

		int posTableroCasa = Casillas.CASILLAS_CASA[jugador.getColorInt()];
		int avances = 1;

		for (int i = 1; i <= numAvances; i++) {

			if (retroceso) {

				avances = -1;
			}

			if (enTableroCasa) {

				if (f.getPosicio() == (posTableroCasa + 8)) {
					retroceso = true;
				}

				if (f.getPosicio() == posTableroCasa) {

					if (tablero[getCasillaEntrada(jugador)].getBloqueado() == Casillas.KEY_BLOQUEADO) {

						break;

					} else {

						f.setPosicio(getCasillaEntrada(jugador));
						enTableroCasa = false;

					}

					f.setPosicio(f.getPosicio() + avances);

				} else {

					bloqueoCasa(jugador, f.getPosicio() + avances);

					f.setPosicio(f.getPosicio() + avances);

				}

				continue;
			}

			if (tablero[(f.getPosicio() + 1) % 67 + 1].getBloqueado() == Casillas.KEY_BLOQUEADO) {
				break;
			} else {
				if (tablero[(f.getPosicio() + 1) % 67 + 1].getPosicion() == getCasillaEntrada(jugador)) {
					enTableroCasa = true;
					f.setPosicio(posTableroCasa);
				}

				f.setPosicio(f.getPosicio() + avances);
			}

		}

		if (enTableroCasa) {

			if (f.getPosicio() == (posTableroCasa + 8)) {

				f.setActiva(false);

			}

			return;
		}

		f2 = hayficha(f);

		if (casillaSegura(f, tablero)) {// casilla segura

			if (f2 != null) {// casilla segura con una ficha = a bloqueo
				tablero[f.getPosicio()].setBloqueado(Casillas.KEY_BLOQUEADO);

			}
			return;

		} else {
			if (f2 != null) {
				if (hayfichaColor(f, f2)) {
					tablero[f.getPosicio()].setBloqueado(Casillas.KEY_BLOQUEADO);
				} else {// eliminar f2
					eliminarFicha(f2);
				}

			}
		}

		// si hay una ficha de un mismo color en una casilla no salvens pues se bloquea
		// tambien; asi q else{hayFichaColor()}

	}

	private boolean hayfichaColor(Fitxes f, Fitxes f2) {
		return f.getJugador().getColor().equals(f2.getJugador().getColor());
	}

	private Fitxes hayficha(Fitxes f) {

		for (int i = 0; i < fitxesPartida.length; i++) {
			if (f.getPosicio() == fitxesPartida[i].getPosicio() && f.getId() != fitxesPartida[i].getId()) {
				return fitxesPartida[i];
			}
		}
		return null;
	}

	private boolean bloqueoCasa(Jugador j, int pos) {

		int cont = 0;

		for (Fitxes fitxes : fitxesPartida) {

			if (fitxes.getJugador().equals(j) && fitxes.getPosicio() == pos) {
				cont++;

				if (cont == 2) {
					return true;
				}
			}

		}

		return false;

	}

	private boolean casillaSegura(Fitxes f, Casillas[] tablero) {
		return tablero[f.getPosicio()].getTipoCasilla().equals(Casillas.STR_TIPO_SEGURA);
	}

	private boolean desbloquearCasilla(Fitxes f, Casillas[] tablero) {
		boolean Debloqueado = false;
		if (tablero[f.getPosicio()].getBloqueado() == Casillas.KEY_BLOQUEADO) {
			tablero[f.getPosicio()].setBloqueado(Casillas.KEY_NO_BLOQUEADO);
			Debloqueado = true;
		}
		return Debloqueado;

	}

	private boolean comprovarFichas(Jugador jugador) {

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

	private Fitxes elegirFicha(Jugador jugador, String accion) {
		String fichasActivas = "";
		int contador = 0;

		ArrayList<Fitxes> fitxes = jugador.getFitxes();

		for (Fitxes ficha : fitxes) {
			if (ficha.isActiva() && ficha.getPosicio() == 0) {
				contador++;
				fichasActivas += contador + " [" + ficha.getPosicio() + "] ";
			}
		}

		System.out.println("Fichas activas: " + fichasActivas);

		if (contador == 0) {
			System.out.println("No hay fichas activas para " + accion + ".");
			return null; // No hay fichas activas, salir del m?todo
		}

		int opcion;
		do {
			System.out.print("Seleccione el nÃºmero de la ficha que desea" + accion + ": ");
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > contador) {
					System.err.println("Error: Seleccione un nÃºmero vÃ¡lido.");
				} else {

					break; // Salir del bucle si la entrada es v?lida
				}
			} else {
				System.err.println("Error: Ingrese un n?mero entero.");
				sc.next(); // Limpiar el buffer de entrada
			}
		} while (true);

		int fichaSeleccionada = 0;
		for (Fitxes ficha : fitxes) {
			if (ficha.isActiva() && ficha.getPosicio() == 0) {
				fichaSeleccionada++;
				if (fichaSeleccionada == opcion) {
					return ficha; // Salir del m?todo despu?s de eliminar la ficha
				}
			}
		}

		return null;
	}

	public void eliminarFicha(Fitxes fitxe) {
		fitxe.setActiva(false);
		fitxe.setPosicio(0);
		System.out.println("Ficha eliminada con ?xito.");
	}
}