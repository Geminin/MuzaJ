package muzyka.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import muzyka.entities.Uzy;
import muzyka.entities.Zam;

@Stateless
public class OrderDao {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	private String login;

	public void create(Zam zam) {
		em.persist(zam);
	}

	public Zam merge(Zam zam) {
		return em.merge(zam);
	}

	public void remove(Zam zam) {
		em.remove(em.merge(zam));
	}

	public Zam find(Object id) {
		return em.find(Zam.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Zam> getFullList() {
		List<Zam> list = null;

		Query query = em.createQuery("SELECT z FROM Zam z");

		// query.setParameter("userId", u.getUserId());

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Zam> getOrderList(Uzy uzy) {
		List<Zam> list = null;

		Query query = em.createQuery("SELECT z From Zam z WHERE z.uzy.id=:userId");

		query.setParameter("userId", uzy.getUserId());

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Zam addToCart(int albumId, Uzy uzy) {

		Query query = em.createNativeQuery("INSERT INTO Zam(albumId, userId) VALUES (:albumId, :userId)");
		query.setParameter("albumId", albumId);
		query.setParameter("userId", uzy.getUserId());
		int rowsAffected = query.executeUpdate();

		return null;

	}

}