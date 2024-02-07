package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import muzyka.dao.*;
import muzyka.entities.*;

@Named
@RequestScoped
public class albumdb{
	private static final String PAGE_Album_EDIT = "albumEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;

	@EJB
	AlbumDao albumDao;
	
	
	public List<Album>getFullListA(){
		return albumDao.getFullListOnly();
	}
	
	
	public String newAlbum(){
		Album album = new Album();
		
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("album", album);
		
		
		return PAGE_Album_EDIT;
	}

	public String editAlbum(Album album){
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("album", album);
		
		//2. Pass object through flash 
		flash.put("album", album);
		
		return PAGE_Album_EDIT;
	}

	public String deleteAlbum(Album album){
		albumDao.remove(album);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	

	
	

}