package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import muzyka.dao.OrderDao;
import muzyka.entities.Uzy;
import muzyka.entities.Zam;

@Named
@RequestScoped
public class orderdb {
	private static final String PAGE_Album_EDIT = "albumEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@Inject
	ExternalContext extcontext;
	
	@EJB
	OrderDao orderDao;

	
	public List<Zam> getFullListO() {
		return orderDao.getFullList();
	}
	@SuppressWarnings("unchecked")
	public List<Zam> getOrderList() {

		RemoteClient<Uzy> remoteClient = RemoteClient.load((HttpSession)extcontext.getSession(true));
		List<Zam> list = orderDao.getOrderList(remoteClient.getDetails());
		return list;
		
	
	}
	
	public String newOrder(){
		Zam zam = new Zam();
		
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("zam", zam);
		
		
		return PAGE_Album_EDIT;
	}

	public String editOrder(Zam zam){
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("zam", zam);
		
		
		
		return PAGE_Album_EDIT;
	}

	public String deleteOrder(Zam zam){
		orderDao.remove(zam);
		return PAGE_STAY_AT_THE_SAME;
	}
}
