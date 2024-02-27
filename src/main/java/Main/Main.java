package Main;

import java.util.Date;

import com.mysql.cj.xdevapi.SessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import logica.Juego;
import modelo.Jugador;
import modelo.Partides;
import pkg_DAO.DAOGeneric;
import pkg_DAO.PartidesDAO;
import pkg_DAO.Utils;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
    	Utils.getSessionFactory();

    	
    	int cont = 0;
    	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    	Jugador a1 = new Jugador("jaun1",Jugador.COLORES[0],2);
        Jugador a2 = new Jugador("jaun2",Jugador.COLORES[1],2);
        Jugador a3 = new Jugador("jaun3",Jugador.COLORES[2],2);
        Jugador a4 = new Jugador("jaun4",Jugador.COLORES[3],2);
        
        Jugador b1 = new Jugador("jaun5",Jugador.COLORES[0],2);
        Jugador b2 = new Jugador("jaun6",Jugador.COLORES[1],2);
        Jugador b3 = new Jugador("jaun7",Jugador.COLORES[2],2);
        Jugador b4 = new Jugador("jaun8",Jugador.COLORES[3],2);
        

        jugadores.add(a1);
        jugadores.add(a2);
        jugadores.add(a3);
        jugadores.add(a4);

        jugadores.add(b1);
        jugadores.add(b2);
        jugadores.add(b3);
        jugadores.add(b4);

        ArrayList<Jugador> jugaores4 = new ArrayList<Jugador>();
    	for (int i = 0; i < jugadores.size(); i++) {
    		
    		jugaores4.add(jugadores.get(i));
    		
    		if(jugaores4.size() == 4) {
    			
    			Partides p = new Partides();
    			PartidesDAO j = new PartidesDAO();
    			j.saveOrUpdate(p);
    			System.out.println("fin");
    			
    			new Juego(p,jugaores4);
    			
    			jugaores4 = new ArrayList<Jugador>();
    			
    		}
		}
    	
        
        

    }
}
