package muzyka.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the zam database table.
 * 
 */
@Entity
@NamedQuery(name="Zam.findAll", query="SELECT z FROM Zam z")
public class Zam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="oder_id")
	private Integer oderId;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="album_id")
	private Album album;

	//bi-directional many-to-one association to Uzy
	@ManyToOne
	@JoinColumn(name="user_id")
	private Uzy uzy;

	public Zam() {
	}

	public Integer getOderId() {
		return this.oderId;
	}

	public void setOderId(Integer oderId) {
		this.oderId = oderId;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Uzy getUzy() {
		return this.uzy;
	}

	public void setUzy(Uzy uzy) {
		this.uzy = uzy;
	}

}