package Main;


import java.util.ArrayList;
import java.util.Date;

import logica.Juego;
import modelo.Jugador;
import modelo.Partides;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
    	//Utils.getSessionFactory();

    	
    	int cont = 0;
    	ArrayList<Jugador> jugadores = (ArrayList<Jugador>) Juego.jDAO.list();
    	//if de get jugadores BBDD y si no hay crearlos :D
    	if(jugadores.isEmpty()) {
    		//ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        	Jugador a1 = new Jugador("jaun1",Jugador.COLORES[0]);
            Jugador a2 = new Jugador("jaun2",Jugador.COLORES[1]);
            Jugador a3 = new Jugador("jaun3",Jugador.COLORES[2]);
            Jugador a4 = new Jugador("jaun4",Jugador.COLORES[3]);
            
            Jugador b1 = new Jugador("jaun5",Jugador.COLORES[0]);
            Jugador b2 = new Jugador("jaun6",Jugador.COLORES[1]);
            Jugador b3 = new Jugador("jaun7",Jugador.COLORES[2]);
            Jugador b4 = new Jugador("jaun8",Jugador.COLORES[3]);
            
            
            jugadores.add(a1);
            jugadores.add(a2);
            jugadores.add(a3);
            jugadores.add(a4);
            
            jugadores.add(b1);
            jugadores.add(b2);
            jugadores.add(b3);
            jugadores.add(b4);
    	}
    	

        

        ArrayList<Jugador> jugaores4 = new ArrayList<Jugador>();
    	for (int i = 0; i < jugadores.size(); i++) {
    		
    		jugaores4.add(jugadores.get(i));
    		Juego.jDAO.saveOrUpdate(jugadores.get(i));// GUARDAR JUGADORES EN LA BASE DE DATOS 
    		if(jugaores4.size() == 4) {
    			Jugador ganador =jugaores4.get(1); 
    			Partides p = new Partides();
    			Juego.pDAO.saveOrUpdate(p);/*
    			
    			p.setFechaFin(Partides.DateAString(new Date()));
                p.setEnCurso(false);
                p.setGanador(ganador);
                ganador.getPartidesGuanyades().add(p);
                ganador.setVictories();
                Juego.pDAO.saveOrUpdate(p);
                Juego.jDAO.saveOrUpdate(ganador);*/
                
    			new Juego(p,jugaores4);
    			
    			jugaores4 = new ArrayList<Jugador>();
    			
    		}
		}
    	
        

    }
}
