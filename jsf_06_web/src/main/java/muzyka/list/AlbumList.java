package muzyka.list;

import java.io.Serializable;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.simplesecurity.LazyCustomerDataModel;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import muzyka.dao.AlbumDao;
import muzyka.dao.OrderDao;
import muzyka.dao.UserDao;
import muzyka.entities.Album;
import muzyka.entities.Uzy;
import muzyka.entities.Zam;

@Named
@ViewScoped
public class AlbumList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
	private Zam zam = new Zam();
	private Album album;
	private LazyDataModel<Album> lazyModel;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	AlbumDao albumDao;

	@EJB
	OrderDao orderDao;

	@EJB
	UserDao userDao;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LazyDataModel<Album> getLazyModel() {
		return lazyModel;
	}

	public AlbumDao getAlbumDao() {
		return albumDao;
	}

	@PostConstruct
	public void init() {
		lazyModel = new LazyCustomerDataModel(this);

	}
	
	public void onRowSelect(SelectEvent<Album> event) {
		FacesMessage msg  = new FacesMessage("Album wybrany", String.valueOf(event.getObject().getAlbumId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

//	public List<Album> getList() {
//		List<Album> list = null;
//
//		// 1. Prepare search params
//		Map<String, Object> searchParams = new HashMap<String, Object>();
//
//		if (title != null && title.length() > 0) {
//			searchParams.put("title", title);
//		}
//		list = albumDao.getList(searchParams);
//		return list;
//
//	}

	public String addToCart(Album album) {

		@SuppressWarnings({ "unchecked" })
		RemoteClient<Uzy> remoteClient = RemoteClient.load((HttpSession) extcontext.getSession(true));

		zam.setAlbum(albumDao.find(album.getAlbumId()));
		zam.setUzy(userDao.find(remoteClient.getDetails().getUserId()));
		orderDao.create(zam);

		return PAGE_STAY_AT_THE_SAME;

	}

	public boolean loggedin() {
		boolean loggedin;

		@SuppressWarnings("unchecked")
		RemoteClient<Uzy> remoteClient = RemoteClient.load((HttpSession) extcontext.getSession(true));

		// Now you can access information like roles, details, etc.
		if (remoteClient != null) {
			// User user = remoteClient.getDetails();
			// Set<String> roles = remoteClient.getRoles();
			loggedin = true;
			return loggedin;
			// Use the information as needed
		} else {
			loggedin = false;
			return loggedin;
		}
	}

}
