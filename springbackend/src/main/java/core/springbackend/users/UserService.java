package core.springbackend.users;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private EntityManager em;
	
	
	@Autowired
	public UserService (EntityManager entityManager) {
		em = entityManager;
	}	
	

}
