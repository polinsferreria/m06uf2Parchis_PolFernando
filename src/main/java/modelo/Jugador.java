package modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
	private ArrayList<Fitxes> fitxes;

	@OneToMany(mappedBy = "guanyador", cascade = CascadeType.ALL)
	private ArrayList<Partides> partidesGuanyades;

	public Jugador(int id, String nom, String color, int victories) {
		super();
		this.id = id;
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
			if (color == c) {
				this.color = color;
			}
		}
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public ArrayList<Fitxes> getFitxes() {
		return fitxes;
	}

	public void setFitxes(ArrayList<Fitxes> fitxes) {
		this.fitxes = fitxes;
	}

	public ArrayList<Partides> getPartidesGuanyades() {
		return partidesGuanyades;
	}

	public void setPartidesGuanyades(ArrayList<Partides> partidesGuanyades) {
		this.partidesGuanyades = partidesGuanyades;
	}

	public static void inicializarFitxes(Partides partida, Jugador jugador) {

		ArrayList<Fitxes> fitxes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			Fitxes f = new Fitxes(i, 0, false, jugador, partida);
			fitxes.add(f);
			
		}
		jugador.setFitxes(fitxes);
	}

	private final void inicializarFitxes() {

		this.fitxes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {

			Fitxes f = new Fitxes(0, false, this);
			fitxes.add(f);

		}

	}

	@Override
	public String toString() {
		return "Jugador{" + "id=" + id + ", nom=" + nom + ", color=" + color + ", victories=" + victories + ", fitxes="
				+ fitxes + ", partidesGuanyades=" + partidesGuanyades + '}';
	}

}