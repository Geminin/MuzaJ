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
 * The persistent class for the uzy database table.
 * 
 */
@Entity
@NamedQuery(name="Uzy.findAll", query="SELECT u FROM Uzy u")
public class Uzy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Lob
	private String login;

	@Lob
	private String password;

	@Lob
	private String role;

	//bi-directional many-to-one association to Zam
	@OneToMany(mappedBy="uzy")
	private List<Zam> zams;

	public Uzy() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Zam> getZams() {
		return this.zams;
	}

	public void setZams(List<Zam> zams) {
		this.zams = zams;
	}

	public Zam addZam(Zam zam) {
		getZams().add(zam);
		zam.setUzy(this);

		return zam;
	}

	public Zam removeZam(Zam zam) {
		getZams().remove(zam);
		zam.setUzy(null);

		return zam;
	}

}