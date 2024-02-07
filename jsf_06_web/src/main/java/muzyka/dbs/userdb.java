package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import muzyka.dao.UserDao;
import muzyka.entities.Uzy;


@Named
@RequestScoped
public class userdb {

	
	@EJB
	UserDao userDao;
	
	public List<Uzy>getFullListU(){
		return userDao.getFullList();
	}
}
