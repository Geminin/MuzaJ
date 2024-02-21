package muzyka.login;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import muzyka.dao.UserDao;
import muzyka.entities.Uzy;

@Named("loginBean")
@RequestScoped
public class login {
	private static final String PAGE_MAIN = "/public/index?faces-redirect=true";
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String password;
	


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	


	@EJB
	UserDao userDAO;

	public String doLogin() {
	    FacesContext ctx = FacesContext.getCurrentInstance();

	    // 1. verify login and password - get User from "database"
	    Uzy uzy = userDAO.getUserFromDatabase(login, password);

	    // 2. if bad login or password - stay with error info
	    if (uzy == null) {
	        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Niepoprawny login lub hasło", null));
	        return PAGE_STAY_AT_THE_SAME;
	    }

	    // 3. if logged in: get User roles, save in RemoteClient and store it in session
	    
	    RemoteClient<Uzy> client = new RemoteClient<Uzy>(); // create new RemoteClient
	    client.setDetails(uzy);

	    List<String> roles = userDAO.getUserRolesFromDatabase(uzy); // get User roles

	    if (roles != null) { // save roles in RemoteClient
	        for (String role : roles) {
	            client.getRoles().add(role);
	        }
	    }

	    // store RemoteClient with request info in session (needed for SecurityFilter)
	    HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
	    client.store(request);

	    // and enter the system (now SecurityFilter will pass the request)
	    // Return the outcome so that it navigates to the PAGE_MAIN
	    return PAGE_MAIN;
	}

	
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return PAGE_LOGIN;
	}


	
}
