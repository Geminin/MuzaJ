package muzyka.dbs;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import muzyka.dao.PerformerDao;
import muzyka.entities.Performer;


@Named
@RequestScoped
public class performerdb {
	
	@EJB
	PerformerDao performerDao;
	
	public List<Performer>getFullListP(){
		return performerDao.getFullList();
	}

}
