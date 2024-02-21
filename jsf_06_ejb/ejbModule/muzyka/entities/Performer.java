package muzyka.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;


/**
 * The persistent class for the performer database table.
 * 
 */
@Entity
@NamedQuery(name="Performer.findAll", query="SELECT p FROM Performer p")
public class Performer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="performer_id")
	private int performerId;

	@Lob
	private String name;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="performer")
	private List<Album> albums;

	public Performer() {
	}

	public Integer getPerformerId() {
		return this.performerId;
	}

	public void setPerformerId(Integer performerId) {
		this.performerId = performerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setPerformer(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setPerformer(null);

		return album;
	}

}