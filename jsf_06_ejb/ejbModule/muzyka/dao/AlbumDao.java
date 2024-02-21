package muzyka.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import muzyka.entities.Album;

@Stateless
public class AlbumDao {

	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Album album) {
		em.persist(album);
	}

	public Album merge(Album album) {
		return em.merge(album);
	}

	public void remove(Album album) {
		em.remove(em.merge(album));
	}

	public Album find(Object id) {
		return em.find(Album.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Album> getFullListOnly() {
		List<Album> list = null;

		Query query = em.createQuery("SELECT a FROM Album a");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
//
	public Long AlbumCount() {
		Long count = null;
		Query query = em.createQuery("SELECT COUNT(a) FROM Album a");// WHERE a.title LIKE :title
		//query.setParameter("title", title + "%");
		try {
			count = (Long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}
//String title,
	public List<Album> findAlbumsByPerformer(int pagesize, int offset, String title) {
		List<Album> list = null;
		
		String select = "select a ";
		String from = "from Album a ";
		String where = "";
		String orderby = "order by a.title asc";
		
		
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.title like :title ";
		}
		Query query = em.createQuery(select + from + where + orderby);
		if (title != null) {
			query.setParameter("title", title + "%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(pagesize);
		
		
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.title like :title ";
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	


//	@SuppressWarnings("unchecked")
//	public List<Album> getList(Map<String, Object> searchParams) {
//
//		List<Album> list = null;
//
//		// 1. Build query string with parameters
//		String select = "select a ";
//		String from = "from Album a ";
//		String where = "";
//		String orderby = "order by a.title asc";
//
//		// search for surname
//		String title = (String) searchParams.get("title");
//		if (title != null) {
//			if (where.isEmpty()) {
//				where = "where ";
//			} else {
//				where += "and ";
//			}
//			where += "a.title like :title ";
//		}
//
//		// 2. Create query object
//		Query query = em.createQuery(select + from + where + orderby);
//
//		// 3. Set configured parameters
//		if (title != null) {
//			query.setParameter("title", title + "%");
//		}
//
//		// 4. Execute query and retrieve list of Person objects
//		try {
//			
//			list = query.getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//
//	}
}
