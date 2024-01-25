package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PARTIDES")
public class Partides {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPartida")
	private int idPartida;

	@Column(name = "FechaInicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Column(name = "FechaFin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "IdGanador")
	private Jugador ganador;

	@Column(name = "EnCurso")
	private boolean enCurso;

	public Partides(int idPartida, Date fechaInicio, Date fechaFin, Jugador ganador, boolean enCurso) {
		super();
		this.idPartida = idPartida;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.ganador = ganador;
		this.enCurso = enCurso;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public boolean isEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}
	
	

}
