package modelo;

import javax.persistence.*;

@Entity
@Table(name = "CASILLAS")
public class Casillas {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCasilla")
    private int idCasilla;

    @Column(name = "TipoCasilla")
    private String tipoCasilla;

    @Column(name = "Posicion")
    private int posicion;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partides partida;
    
    

	public Casillas(int idCasilla, String tipoCasilla, int posicion, Partides partida) {
		super();
		this.idCasilla = idCasilla;
		this.tipoCasilla = tipoCasilla;
		this.posicion = posicion;
		this.partida = partida;
	}

	public int getIdCasilla() {
		return idCasilla;
	}

	public void setIdCasilla(int idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(String tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Partides getPartida() {
		return partida;
	}

	public void setPartida(Partides partida) {
		this.partida = partida;
	}
    
    

}
