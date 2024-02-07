package muzyka.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private int oderId;

	@Column(name="album_id")
	private int albumId;

	@Column(name="user_id")
	private int userId;

	public Zam() {
	}

	public int getOderId() {
		return this.oderId;
	}

	public void setOderId(int oderId) {
		this.oderId = oderId;
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}