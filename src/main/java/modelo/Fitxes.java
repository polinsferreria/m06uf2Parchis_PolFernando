package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "FITXERS")
public class Fitxes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFitxa")
    private int id;

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

    public Fitxes(int id, int posicio, boolean activa, Jugador jugador, Partides partida) {
        super();
        this.id = id;
        this.posicio = posicio;
        this.activa = activa;
        this.jugador = jugador;
        this.partida = partida;
    }

    public Fitxes(int id,int posicio, boolean activa) {
        this.posicio = posicio;
        this.activa = activa;
    }

    public Fitxes(int id, boolean activa, Jugador jugador) {
        this.activa = activa;
        this.jugador = jugador;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Fitxes{" + "posicio=" + posicio + ", activa=" + activa + ", jugador=" + jugador + ", partida=" + partida + '}';
    }

}