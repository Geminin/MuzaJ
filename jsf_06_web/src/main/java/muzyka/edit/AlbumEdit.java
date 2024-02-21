package muzyka.edit;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import muzyka.dao.AlbumDao;
import muzyka.entities.Album;

@Named
@ViewScoped
public class AlbumEdit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ALBUM_LIST = "/db/dbView?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Album album = new Album();
	private Album loaded = null;

	@EJB
	AlbumDao albumDao;

	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;

	public Album getAlbum() {
		return album;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		//HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//loaded = (Album) session.getAttribute("album");

		// 2. load person passed through flash
		loaded = (Album) flash.get("album");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			 album = loaded;
			 //session.removeAttribute("album");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			 if (!context.isPostback()) { //possible redirect
			 context.getExternalContext().redirect("../../db/dbView.xhtml");
			 context.responseComplete();
			 }
		}

	}

	public String saveData() {
		// no Album object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (album.getAlbumId() == null) {
				// new record
				albumDao.create(album);
			} else {
				// existing record
				albumDao.merge(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ALBUM_LIST;
	}

}
