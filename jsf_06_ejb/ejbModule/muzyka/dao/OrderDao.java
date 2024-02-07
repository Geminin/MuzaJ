package muzyka.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import muzyka.entities.Album;
import muzyka.entities.Uzy;
import muzyka.entities.Zam;





@Stateless
public class OrderDao{
	private final static String UNIT_NAME = "jsfcourse-simplePU";
	
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
	public List<Zam> getFullList(Uzy u){
		List<Zam> list = null;
		
		
		Query query = em.createQuery("SELECT z From Zam WHERE z.userId=5");
		
		 query.setParameter("userId", u.getUserId());
		 
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Zam> getFullList(Uzy u){
		List<Zam> list = null;
		
		
		Query query = em.createQuery("SELECT z From Zam WHERE z.userId=:userId");
		
		 query.setParameter("userId", u.getUserId());
		 
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}*/

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
	public Zam addToCart(int albumId, int userId) {
		
		Query query = em.createQuery("INSERT INTO Zam (user_id,album_id) VALUES (:userId,:albumId)");
		
		return null;
		
	}


    
}