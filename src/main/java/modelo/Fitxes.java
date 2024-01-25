package modelo;

import javax.persistence.*;

@Entity
@Table(name = "FITXERS")
public class Fitxes {

	@Column(name = "Posicio")
	private int posicio;

	@Column(name = "Activa")
	private boolean activa;

	@ManyToOne
	@JoinColumn(name = "IdJugador")
	private Jugador jugador;

	@ManyToOne
	@JoinColumn(name = "IdPartida")
	private Partides partida;

	public Fitxes(int posicio, boolean activa, Jugador jugador, Partides partida) {
		super();
		this.posicio = posicio;
		this.activa = activa;
		this.jugador = jugador;
		this.partida = partida;
	}

	public int getPosicio() {
		return posicio;
	}

	public void setPosicio(int posicio) {
		this.posicio = posicio;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partides getPartida() {
		return partida;
	}

	public void setPartida(Partides partida) {
		this.partida = partida;
	}
	
	
	
	
	

}
