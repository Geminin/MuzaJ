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
import muzyka.entities.*;

@Named
@RequestScoped
public class orderdb {
	
	@Inject
	ExternalContext extcontext;
	
	@EJB
	OrderDao orderDao;

	public List<Zam> getFullListO() {
		RemoteClient<Uzy> remoteClient = RemoteClient.load((HttpSession)extcontext.getSession(true));
		List<Zam> list = orderDao.getFullList(remoteClient.getDetails());
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Zam> getList() {

		RemoteClient<Uzy> remoteClient = RemoteClient.load((HttpSession)extcontext.getSession(true));
		
		return orderDao.getzamList(remoteClient.getDetails());
		
		

	}
}
