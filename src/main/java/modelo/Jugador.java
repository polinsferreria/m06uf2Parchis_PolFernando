
package modelo;

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
public class Jugador {

	
	private static final String[] COLORES = {"ROJO", "AMARILLO", "AZUL","VERDE"};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVehicle")
	private int id;

	@Column(name = "NOM")
	private String nom;

	@Column(name = "Color")
	private String color;

	@Column(name = "Victories")
	private int victories;

	@OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private List<Fitxes> fitxes;

    @OneToMany(mappedBy = "guanyador", cascade = CascadeType.ALL)
    private List<Partides> partidesGuanyades;
    
	public Jugador(int id, String nom, String color, int victories) {
		super();
		this.id = id;
		this.nom = nom;
		this.color = color;
		this.victories = victories;
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
		this.color = color;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}
	
	

}
