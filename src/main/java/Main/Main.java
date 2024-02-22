package Main;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import logica.Juego;
import modelo.Fitxes;
import modelo.Jugador;
import modelo.Partides;

public class Main {
    
    public static void main(String[] args) {
        // TODO code application logic here
    	int cont = 0;
    	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    	Jugador a1 = new Jugador(1,"jaun1",Jugador.COLORES[0],2);
        Jugador a2 = new Jugador(2,"jaun2",Jugador.COLORES[1],2);
        Jugador a3 = new Jugador(3,"jaun3",Jugador.COLORES[2],2);
        Jugador a4 = new Jugador(4,"jaun4",Jugador.COLORES[3],2);
        
        Jugador b1 = new Jugador(5,"jaun5",Jugador.COLORES[0],2);
        Jugador b2 = new Jugador(6,"jaun6",Jugador.COLORES[1],2);
        Jugador b3 = new Jugador(7,"jaun7",Jugador.COLORES[2],2);
        Jugador b4 = new Jugador(8,"jaun8",Jugador.COLORES[3],2);
        
        jugadores.add(a1);
        jugadores.add(a2);
        jugadores.add(a3);
        jugadores.add(a4);
        
        jugadores.add(b1);
        jugadores.add(b2);
        jugadores.add(b3);
        jugadores.add(b4);
        /*Partides p1 = new Partides(1,);*/
        ArrayList<Jugador> jugaores4 = new ArrayList<Jugador>();
    	for (int i = 0; i < jugadores.size(); i++) {
    		
    		jugaores4.add(jugadores.get(i));
    		if(jugaores4.size() == 4) {
    			
    			new Juego(new Partides(cont),jugaores4);
    			
    			cont++;
    			jugaores4 = new ArrayList<Jugador>();
    		}
		}
    	
        
        
    }
    
        
}
