package Main;

import java.util.ArrayList;
import logica.Juego;
import modelo.Fitxes;
import modelo.Jugador;

public class Main {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Jugador j = new Jugador(1,"jaun",Jugador.COLORES[0],2);
        
        System.out.println(Juego.getCasillaEntrada(j));
    }
    
        
}
