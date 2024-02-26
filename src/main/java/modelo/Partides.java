package modelo;

import java.text.SimpleDateFormat;
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
	private String fechaInicio;

	@Column(name = "FechaFin")
	private String fechaFin;

	@ManyToOne
	@JoinColumn(name = "IdGanador")
	private Jugador ganador;

	@Column(name = "EnCurso")
	private boolean enCurso;

	public Partides(int idPartida, String fechaInicio, String fechaFin, Jugador ganador, boolean enCurso) {
		super();
		this.idPartida = idPartida;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.ganador = ganador;
		this.enCurso = enCurso;
	}

	public Partides() {
		super();
		this.fechaInicio = DateAString(new Date());
		this.enCurso = true;
	}
	
	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
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
	public void finalizarPartida() {
	    this.fechaFin = DateAString(new Date());
	    this.enCurso = false;
	}
	
	public static String DateAString(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 // Convertir Date a String
        String fechaString = formato.format(fecha);
        return fechaString;
	}
	@Override
	public String toString() {
		return "Partides [idPartida=" + idPartida + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", ganador=" + ganador + ", enCurso=" + enCurso + "]";
	}
	
	

}
