package muzyka.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import muzyka.dao.*;
import muzyka.entities.*;


@Named
@RequestScoped
public class AlbumList {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
	private Zam zam = new Zam();
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	AlbumDao albumDao;
	
	@EJB
	OrderDao orderDao;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Album> getFullList(){
		return albumDao.findAlbumsByPerformer(0);
	}
	
	public List<Album> getList(){
		List<Album> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		//2. Get list
		list = albumDao.getList(searchParams);
		
		return list;
	}

	
	public String addToCart(int albumId){
		
		@SuppressWarnings({ "unused", "unchecked" })
		RemoteClient<Uzy> remoteClient = RemoteClient.load(
			    (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		
		Uzy uzy = remoteClient.getDetails();
		int userId = remoteClient.getUserId();
		
		
		
		orderDao.addToCart(albumId,userId);
		
		return PAGE_STAY_AT_THE_SAME;

	}


	
	public boolean loggedin() {
		boolean loggedin;
		
		@SuppressWarnings("unchecked")
		RemoteClient<Uzy> remoteClient = RemoteClient.load(
			    (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true));

			// Now you can access information like roles, details, etc.
			if (remoteClient != null) {
			    //User user = remoteClient.getDetails();
			   // Set<String> roles = remoteClient.getRoles();
			    loggedin = true;
			    return loggedin;
			    // Use the information as needed
			}
			else {
				loggedin = false;
				return loggedin;
			}
	}


}
