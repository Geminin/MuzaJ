package muzyka.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;


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

	public Performer() {
	}

	public int getPerformerId() {
		return this.performerId;
	}

	public void setPerformerId(int performerId) {
		this.performerId = performerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}