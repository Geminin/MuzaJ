package muzyka.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;



/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_id")
	private Integer albumId;

	@Lob
	private String genre;

	private Integer price;

	@Lob
	private String title;

	@Lob
	private String tracks;

	//bi-directional many-to-one association to Performer
	@ManyToOne
	@JoinColumn(name="performer_id")
	private Performer performer;

	//bi-directional many-to-one association to Zam
	@OneToMany(mappedBy="album")
	private List<Zam> zams;

	public Album() {
	}

	public Integer getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTracks() {
		return this.tracks;
	}

	public void setTracks(String tracks) {
		this.tracks = tracks;
	}

	public Performer getPerformer() {
		return this.performer;
	}

	public void setPerformer(Performer performer) {
		this.performer = performer;
	}

	public List<Zam> getZams() {
		return this.zams;
	}

	public void setZams(List<Zam> zams) {
		this.zams = zams;
	}

	public Zam addZam(Zam zam) {
		getZams().add(zam);
		zam.setAlbum(this);

		return zam;
	}

	public Zam removeZam(Zam zam) {
		getZams().remove(zam);
		zam.setAlbum(null);

		return zam;
	}

}