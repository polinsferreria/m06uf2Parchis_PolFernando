package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "JUGADOR")
public class Jugador implements Serializable {

	public static final String[] COLORES = { "ROJO", "AMARILLO", "AZUL", "VERDE" };

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idJugador")
	private int id;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "Color")
	private String color;

	@Column(name = "Victories")
	private int victories;

	@OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
	private List<Fitxes> fitxes;
	
	@OneToMany(mappedBy = "ganador", cascade = CascadeType.ALL)
	private List<Partides> partidesGuanyades;

	public Jugador(String nom, String color, int victories) {
		super();
		this.nom = nom;
		this.color = color;
		this.victories = victories;
		
		inicializarFitxes();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		for (String c : COLORES) {
	        if (color.equals(c)) {
	            this.color = color;
	            return;  // Agregamos un return para salir del bucle si encontramos el color
	        }
	    }
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public List<Fitxes> getFitxes() {
		return fitxes;
	}

	public void setFitxes(ArrayList<Fitxes> fitxes) {
		this.fitxes = fitxes;
	}

	public List<Partides> getPartidesGuanyades() {
		return partidesGuanyades;
	}

	public void setPartidesGuanyades(List<Partides> partidesGuanyades) {
		this.partidesGuanyades = partidesGuanyades;
	}

	public static void inicializarFitxes(Partides partida, Jugador jugador) {

		ArrayList<Fitxes> fitxes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			Fitxes f = new Fitxes( 0, false, jugador, partida);
			fitxes.add(f);
			
		}
		jugador.setFitxes(fitxes);
	}
	public int getColorInt() {
        for (int i = 0; i < COLORES.length; i++) {
            if (COLORES[i].equals(this.color)) {
                return i;
            }
        }
        return -1;
    }

	private final void inicializarFitxes() {

		this.fitxes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			Fitxes f = new Fitxes( false, this);
			fitxes.add(f);

		}

	}

	@Override
	public String toString() {
		return "Jugador{" + "id=" + id + ", nom=" + nom + ", color=" + color + ", victories=" + victories + ", partidesGuanyades=" + partidesGuanyades + '}';
	}

}