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
import muzyka.dao.PerformerDao;
import muzyka.entities.Performer;

@Named
@ViewScoped
public class PerformerEdit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PAGE_PERSON_LIST = "/db/dbView?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Performer performer = new Performer();
	private Performer loaded = null;

	@EJB
	PerformerDao performerDao;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Performer getPerformer() {
		return performer;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession)
		// context.getExternalContext().getSession(true);
		// loaded = (Performer) session.getAttribute("performer");

		// 2. load person passed through flash
		loaded = (Performer) flash.get("performer");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			performer = loaded;
			// session.removeAttribute("performer");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (performer.getPerformerId() == null) {
				// new record
				performerDao.create(performer);
			} else {
				// existing record
				performerDao.merge(performer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}
