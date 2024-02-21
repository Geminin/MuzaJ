package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import muzyka.dao.PerformerDao;
import muzyka.entities.Performer;

@Named
@RequestScoped
public class performerdb {

	private static final String PAGE_Album_EDIT = "/tpl/dbs/performerEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	 private Performer performer;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	PerformerDao performerDao;
	
	
	
	public List<Performer> getFullListP() {
		return performerDao.getFullList();
	}

	public String newPerfromer() {
		Performer performer = new Performer();

		// 1. Pass object through session
		// HttpSession session = (HttpSession) extcontext.getSession(true);
		// session.setAttribute("performer", performer);
		// 2. Pass object through flash
		flash.put("performer", performer);

		return PAGE_Album_EDIT;
	}

	public String editPerformer(Performer performer) {
		// 1. Pass object through session
		// HttpSession session = (HttpSession) extcontext.getSession(true);
		// session.setAttribute("performer", performer);
		// 2. Pass object through flash
		flash.put("performer",performer);

		return PAGE_Album_EDIT;
	}

	public String deletePerformer(Performer performer) {
		performerDao.remove(performer);
		return PAGE_STAY_AT_THE_SAME;
	}

	public Performer getPerformer() {
		return performer;
	}

	public void setPerformer(Performer performer) {
		this.performer = performer;
	}



}
