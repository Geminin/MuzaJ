package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import muzyka.dao.UserDao;
import muzyka.entities.Uzy;

@Named
@RequestScoped
public class userdb {
	
	private static final String PAGE_Album_EDIT = "/tpl/users/userEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	UserDao userDao;

	public List<Uzy> getFullListU() {
		return userDao.getFullList();
	}

	public String newUser() {
		Uzy uzy = new Uzy();

		// 1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("uzy", uzy);
		//2. Pass object through flash	
		flash.put("uzy", uzy);

		return PAGE_Album_EDIT;
	}

	public String editUser(Uzy uzy) {
		// 1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("uzy", uzy);
		//2. Pass object through flash	
				flash.put("uzy", uzy);

		return PAGE_Album_EDIT;
	}

	public String deleteUser(Uzy uzy) {
		userDao.remove(uzy);
		return PAGE_STAY_AT_THE_SAME;
	}
}
