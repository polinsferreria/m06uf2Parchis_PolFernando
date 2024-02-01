package modelo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Casillas {

    private static final int[] CASILLAS_SEGURAS = {12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68};

    private static final int[] CASILLAS_SALIDA = {5, 22, 39, 56};

    private int idCasilla;

    private String tipoCasilla;

    private int posicion;

    private Partides partida;

    public Casillas(int idCasilla, String tipoCasilla, int posicion, Partides partida) {
        super();
        this.idCasilla = idCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posicion = posicion;
        this.partida = partida;
    }

    public int getIdCasilla() {
        return idCasilla;
    }

    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }

    public String getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(String tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Partides getPartida() {
        return partida;
    }

    public void setPartida(Partides partida) {
        this.partida = partida;
    }

    public static Casillas[] tablero(Partides partida) {

        Casillas[] tablero = new Casillas[68];

        int cont = 0;

        for (int i = 1; i < 69; i++) {

            String tipo = "CASILLA_NO_SEGURA";

            if (Arrays.asList(CASILLAS_SEGURAS).contains(i)) {

                tipo = "CASILLAS_SEGURAS";

            } else if (Arrays.asList(CASILLAS_SALIDA).contains(i)) {

                String color = Jugador.COLORES[cont];

                tipo = "CASILLA_SALIDA" + color;
                cont++;

            }

            Casillas aux = new Casillas(i, tipo, i, partida);   
            tablero[i - 1] = aux;
        }

        return tablero;
    }
    
    
}
