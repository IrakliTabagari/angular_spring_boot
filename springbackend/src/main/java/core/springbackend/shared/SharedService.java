package core.springbackend.shared;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.springbackend.shared.entities.State;

@Service
public class SharedService {
	
	private EntityManager em;
	
	
	@Autowired
	public SharedService (EntityManager entityManager) {
		em = entityManager;
	}
	
	public List<State> getAllStates(){
		
//	    return em.createQuery(em.getCriteriaBuilder().createQuery(State.class).select(em.getCriteriaBuilder().createQuery(State.class).from(State.class))).getResultList();
	    
		return em.createQuery("SELECT st FROM State st", State.class).getResultList();
	}

}
