package muzyka.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import muzyka.entities.Performer;


@Stateless
public class PerformerDao{
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	
	
	public void create(Performer performer) {
		em.persist(performer);
	}
	
	public Performer merge(Performer performer) {
		 return em.merge(performer);
	}
	
	public void remove(Performer performer) {
		em.remove(em.merge(performer));
	}
	
	public Performer find(Object id) {
		return em.find(Performer.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Performer> getFullList(){
		List<Performer> list = null;
		
		Query query = em.createQuery("SELECT p FROM Performer p");
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
    
}