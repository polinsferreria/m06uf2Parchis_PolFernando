package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Dado {

	private int tiradas;

	private ArrayList<Integer> resultado;

	private final int MAX = 6;

	private final int MIN = 1;

	private boolean eliminado;
	
	private int contador;

	public Dado() {
		
		tiradas = 1;
		contador = 0;
		resultado = new ArrayList<Integer>();
		tirarDado();
	}

	private void tirarDado() {

		Random rnd = new Random();

		int dado1 = rnd.nextInt(MAX - MIN + 1) + MIN;

		int dado2 = rnd.nextInt(MAX - MIN + 1) + MIN;

		resultado.add(dado1);

		resultado.add(dado2);

		if (dado1 == dado2) {

			if (tiradas == 4) {

				eliminado = true;

			} else {

				tiradas++;

				tirarDado();

			}

		}

	}
	

	public ArrayList<Integer> getResultado() {
		
		ArrayList<Integer> aux = new ArrayList<Integer>();
		
		
		if (contador < resultado.size()) {
			
			for (int i = contador; i < (contador + 2); i++) {
				
				aux.add(resultado.get(i));
						
			}
			
			contador += 2;
			
		}
		
		
		return aux;
		
	}

}
