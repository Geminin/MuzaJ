package muzyka.dao;


import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import muzyka.entities.Uzy;

@Stateless
public class UserDao {

	
	@PersistenceContext
    private EntityManager em;

	
	
	public Uzy merge(Uzy uzy) {
		 return em.merge(uzy);
	}
	
	public void remove(Uzy uzy) {
		em.remove(em.merge(uzy));
	}
	
	public Uzy find(Object id) {
		return em.find(Uzy.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Uzy> getFullList() {
		List<Uzy> list = null;
		
		Query query = em.createQuery("SELECT u FROM Uzy u");
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
    
	public Uzy getUserFromDatabase(String login, String password) {
		Uzy u = null;

		Query query = em.createQuery("SELECT u FROM Uzy u where u.login=:login and u.password=:password");
		query.setParameter("login", login);
		query.setParameter("password", password);
		
		try {
			Uzy uzy =  (Uzy) query.getSingleResult();
			u = new Uzy();
			u.setUserId(uzy.getUserId());
			u.setLogin(login);
			u.setPassword(password);
			u.setRole(uzy.getRole());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public List<String> getUserRolesFromDatabase(Uzy uzy) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (uzy.getRole().equals("User")) {
			roles.add("user");
		}
		if (uzy.getRole().equals("Worker")) {
			roles.add("worker");
		}
		if (uzy.getRole().equals("Admin")) {
			roles.add("admin");
		}
		
		return roles;
	}

	
	
	

}