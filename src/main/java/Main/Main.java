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
    	/*Jugador a1 = new Jugador(1,"jaun",Jugador.COLORES[0],2);
        Jugador a2 = new Jugador(2,"jaun",Jugador.COLORES[0],2);
        Jugador a3 = new Jugador(3,"jaun",Jugador.COLORES[0],2);
        Jugador a4 = new Jugador(4,"jaun",Jugador.COLORES[0],2);
        
        Jugador b1 = new Jugador(5,"jaun",Jugador.COLORES[0],2);
        Jugador b2 = new Jugador(6,"jaun",Jugador.COLORES[0],2);
        Jugador b3 = new Jugador(7,"jaun",Jugador.COLORES[0],2);
        Jugador b4 = new Jugador(8,"jaun",Jugador.COLORES[0],2);
        
        Partides p1 = new Partides(1,);*/
    	
    	// Crear un objeto Date
        Date fecha = new Date();

        // Crear un formato de fecha deseado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Convertir Date a String
        String fechaString = formato.format(fecha);

        // Imprimir el resultado
        System.out.println("Fecha como String: " + fechaString);
        
        
    }
    
        
}
