/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
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
            for (int i = 0; i < jugadoresOrdenados.length; i++) {
                if (jugadoresOrdenados[i] != null) {
                    tirarDado(jugadoresOrdenados[i], 1);
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

        // Crear un arreglo de jugadores ordenados seg√∫n los resultados de lanzamiento
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
        fitxesPartida = new Fitxes[16];
        int cont = 0;
        // los jugadores
        for (int i = 0; i <= jugadoresOrdenados.length - 1; i++) {
            for (int j = 0; j < jugadoresOrdenados[i].getFitxes().size(); j++) {
                if (jugadoresOrdenados[i].getFitxes().get(j).getPartida().getIdPartida() == partida.getIdPartida()) {
                    fitxesPartida[cont] = jugadoresOrdenados[i].getFitxes().get(j);
                    fitxesPartida[i].setPosicio(0);                   
                    cont++;
                }
            }
        }      
    }

    public void tirarDado(Jugador jugador, int cont) {// el cont es para saber cuantas veces tira

        Jugador ganador = victoria();
        if (ganador != null) {
            // FINALIZO LA PARTIDA
            System.out.println("Partida finalizada el ganador ha sido " + ganador.getNom());
            return;
        }

        System.out.println("Jugador " + jugador.getNom() + " tira Dados...");
        sc.nextLine();

        Dado d = new Dado();
        ArrayList<Integer> resultado = d.getResultado();
        System.out.println("Ha sacado un " + resultado.get(0) + " y un " + resultado.get(1));

        // SI UN DADO SACA UN 5, PUEDE SACAR FICHA
        if ((resultado.get(0) == 5 || resultado.get(1) == 5) && comprovarFichas(jugador) && Sacarficha(jugador)) {
            if (resultado.get(0) == resultado.get(1) && cont < 4) {
                tirarDado(jugador, ++cont);
            }
            return;
        }
        // SI SALEN DOBLES, MUEBE LA FICHA Y VUELVE A TIRAR
        if ((resultado.get(0) == resultado.get(1)) && cont < 4) {
            System.out.println("Felices salieron dobles es la " + cont + "™ vez  consecutiva");
            moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
            tirarDado(jugador, ++cont);
            return;

        // SI ES LA 3™ VEZ QUE SALEN SE ELIMINA LA FICHA QUE EL ESCOJA
        } else if (resultado.get(0) == resultado.get(1)) {
            System.out.println("Lastima perdiste una ficha... ");
            eliminarFicha(elegirFicha(jugador, "Eliminar"));
            return;
        }
        
        //SI NO SE CUMPLE LOS IF ANTERIORES LLEGA AQUI
        moverFitxa(jugador, resultado.get(0) + resultado.get(1), tablero);
    }
   

    public boolean Sacarficha(Jugador jugador) {

        // EN CASO DE QUE ESTE BLOQUEADA LA CASILLA NO PUEDE SACAR
        if (tablero[getCasillaEntrada(jugador) - 1 % 67].getBloqueado() == Casillas.KEY_BLOQUEADO) {
            System.out.println("La casilla de salida esta bloqueado.");
            return false;
        }

        // SE IMPRIME LA INFO DE LA PARTIDA
        infoPartida(jugador);
        // PREGUNTA
        System.out.println("Quieres sacar una ficha? [Si / No]");
        // BUCLE PARA OBTENER SOLO SI O NO
        do {
            String res = sc.nextLine();
            if (res.equals("Si")) {
                // EN CASO DE SI BUSCAMOS LA PRIMERA FICHA NO ACTIVA QUE TENGA POSICION 0 (SI LA TIENEN DIFERENTE ES QUE HABRAN GANADO)
                for (Fitxes fitxa : jugador.getFitxes()) {
                    if (!fitxa.isActiva() && fitxa.getPosicio() == 0) {                       
                        fitxa.setPosicio(getCasillaSalida(jugador));
                        fitxa.setActiva(true);
                        // SE BLOQUEA LA CASILLA SI ENCUENTRA UNA FICHA
                        if (hayficha(fitxa) != null) {
                            tablero[getCasillaSalida(jugador) - 1 % 67].setBloqueado(Casillas.KEY_BLOQUEADO);
                        }
                       // System.out.println(fitxa.getId() + " " + fitxa.getJugador().getNom() + " " + fitxa.isActiva());
                        return true;
                    }
                }
            } else if (res.equals("No")) {
                return false;
            }
            System.out.println("Error, forato no adecuado");
        } while (true);
    }

    private int getCasillaSalida(Jugador jugador) {
        return Casillas.CASILLAS_SALIDA[jugador.getColorInt()];
    }

    public int getCasillaEntrada(Jugador jugador) {
        int CE = getCasillaSalida(jugador) - 5;
        return CE == 0 ? 67 : CE;

    }

    public void moverFitxa(Jugador jugador, int numAvances, Casillas[] tablero) {
        Fitxes f = elegirFicha(jugador, " mover ");
       // System.out.println("");
        Fitxes f2 = null;
        // System.out.println(f);
        if (f != null) {

            boolean enTableroCasa = f.getPosicio() > 68;
            boolean retroceso = false;

            if (!enTableroCasa) {
                desbloquearCasilla(f, tablero);
            }

            int posTableroCasa = Casillas.CASILLAS_CASA[jugador.getColorInt()];           
            int avances = 1;

            for (int i = 1; i <= numAvances; i++) {

                if (retroceso) {

                    avances = -1;
                }

                if (enTableroCasa) {

                    if (f.getPosicio() >= (posTableroCasa + 8)) {
                        retroceso = true;
                        avances = -1;
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

                if (tablero[(f.getPosicio()) % 67].getBloqueado() == Casillas.KEY_BLOQUEADO) {
                    break;
                } else {
                    if (tablero[(f.getPosicio()) % 67].getPosicion() == getCasillaEntrada(jugador)) {
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

            f.setPosicio(f.getPosicio() % 67);

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
        return tablero[f.getPosicio() - 1 % 67].getTipoCasilla().equals(Casillas.STR_TIPO_SEGURA);
    }

    private boolean desbloquearCasilla(Fitxes f, Casillas[] tablero) {
        boolean Debloqueado = false;
        if (tablero[f.getPosicio() - 1 % 67].getBloqueado() == Casillas.KEY_BLOQUEADO) {
            tablero[f.getPosicio() - 1 % 67].setBloqueado(Casillas.KEY_NO_BLOQUEADO);
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
            if (ficha.isActiva() || ficha.getPosicio() != 0) {
                if (!ficha.isActiva() && ficha.getPosicio() != 0) {
                    continue;
                }
                contador++;
                if (ficha.getPosicio() == 0) {
                    fichasActivas += contador + " [" + getCasillaSalida(ficha.getJugador()) + "] ";
                } else if (ficha.getPosicio() > 68){
                     fichasActivas += contador + " [c" + (ficha.getPosicio() - Casillas.CASILLAS_CASA[jugador.getColorInt()]) + "] ";
                }else{
                    fichasActivas += contador + " [" + ficha.getPosicio() + "] ";
                }

            }
        }

        if (contador == 0) {
            System.out.println("No hay fichas activas para " + accion + ".");
            return null; // No hay fichas activas, salir del m?todo
        }

        infoPartida(jugador);

        System.out.println("Fichas activas: " + fichasActivas);

        int opcion;
        do {
            System.out.print("Seleccione el numero de la ficha que desea" + accion + ": ");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();

                if (opcion < 1 || opcion > contador) {
                    System.err.println("Error: Seleccione un n√É¬∫mero v√É¬°lido.");
                } else {

                    break; // Salir del bucle si la entrada es v?lida
                }
            } else {
                System.err.println("Error: Ingrese un n?mero entero.");

            }
            sc.next(); // Limpiar el buffer de entrada
        } while (true);

        int fichaSeleccionada = 0;
        for (Fitxes ficha : fitxes) {
            if (ficha.isActiva() || ficha.getPosicio() != 0) {
                if (!ficha.isActiva() && ficha.getPosicio() != 0) {
                    continue;
                }
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

    private void infoPartida(Jugador jugadorT) {
        System.out.println("--------------------------------------------------");
        System.out.println("Estado de la partida:");
        System.out.println("Turno del jugador " + jugadorT.getNom());

        for (Jugador jugador : jugadoresOrdenados) {

            System.out.println("Fichas activas del jugador: " + jugador.getNom());

            for (Fitxes ftx : fitxesPartida) {
                if (ftx.isActiva() && ftx.getJugador().equals(jugador)) {
                    if (ftx.getPosicio() < 68) {
                        System.out.println("Ficha " + ftx.getId() + " - PosiciÛn: " + ftx.getPosicio() + " - Casilla " + tablero[(ftx.getPosicio() - 1) % 67].getTipoCasilla());
                    } else {
                        System.out.println("Ficha " + ftx.getId() + " - PosiciÛn: c" + (ftx.getPosicio() - Casillas.CASILLAS_CASA[jugador.getColorInt()]) + " - Casilla Casa");
                    }

                } else if (!ftx.isActiva() && ftx.getPosicio() > 100 && ftx.getJugador().equals(jugador)) {
                    System.out.println("Ficha " + ftx.getId() + " - Ha llegado a casa!");
                }

            }

            System.out.println(); // LÌnea en blanco para separar la informaciÛn de cada jugador
        }

        System.out.println("--------------------------------------------------");
    }

    // Si devuelve null no ha ganado nadie
    public Jugador victoria() {

        for (Jugador jugador : jugadoresOrdenados) {
            int cont = 0;
            for (Fitxes ftxs : jugador.getFitxes()) {
                if (!ftxs.isActiva() && ftxs.getPosicio() > 100) {
                    cont++;
                }

                if (cont == 4) {
                    return ftxs.getJugador();
                }
            }

        }

        return null;
    }

}
