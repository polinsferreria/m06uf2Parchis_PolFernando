package modelo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Casillas {
    
    public static final String STR_TIPO_INSEGURA = "CASILLA_NO_SEGURA";
    
    public static final String STR_TIPO_SEGURA = "CASILLAS_SEGURAS";
    
    public static final String STR_TIPO_SALIDA = "CASILLAS_SALIDA";
    
    public static final int KEY_BLOQUEADO = 1;
    
    public static final int KEY_NO_BLOQUEADO = 0;
    
    public static final int[] CASILLAS_SEGURAS = {12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68};

    public static final int[] CASILLAS_SALIDA = {5, 22, 39, 56};
    
    public static final int[] CASILLAS_CASA = {100,200,300,400};
    
    private int idCasilla;

    private String tipoCasilla;

    private int posicion;
    
    private int bloqueado;

    private Partides partida;

    public Casillas(int idCasilla, String tipoCasilla, int posicion, Partides partida) {
        super();
        this.idCasilla = idCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posicion = posicion;
        this.partida = partida;
        this.bloqueado = KEY_NO_BLOQUEADO;
           
    }

    public Casillas(int idCasilla, String tipoCasilla, int posicion) {
        this.idCasilla = idCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posicion = posicion;
    }
    
        
    public int getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(int bloqueado) {
		this.bloqueado = bloqueado;
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

    public static Casillas[] tablero() {
        Casillas[] tablero = new Casillas[68];
        int cont = 0;
        int cont2 = 0;

        for (int i = 1; i <= 68; i++) {
            String tipo = STR_TIPO_INSEGURA;

            if (i == CASILLAS_SEGURAS[cont2]) {
                tipo = STR_TIPO_SEGURA;
                cont2++;
            } else if (i == CASILLAS_SALIDA[cont]) {
                //String color = Jugador.COLORES[cont];
                tipo = STR_TIPO_SEGURA;
                //cont++;
            }

            Casillas aux = new Casillas(i, tipo, i);
            tablero[i - 1] = aux;
        }

        return tablero;
    }
    public static Casillas[] casaTablero() {
        Casillas[] tablero = new Casillas[68];

        for (int i = 1; i <= 8; i++) {

            String tipo = STR_TIPO_SEGURA;
            Casillas aux = new Casillas(i, tipo, i);
            tablero[i - 1] = aux;
        }

        return tablero;
    }
    
}