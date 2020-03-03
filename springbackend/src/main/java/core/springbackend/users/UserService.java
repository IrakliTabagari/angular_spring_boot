package core.springbackend.users;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.springbackend.users.entities.User;

@Service
public class UserService {
	
	private EntityManager em;
	
	
	@Autowired
	public UserService (EntityManager entityManager) {
		em = entityManager;
	}	
	

	public List<User> getAllUsers() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}
	
	public User getUserByUserName(String userName) {
		User user = null;
		try {
			user = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class)
					.setParameter("userName", userName)
					.getSingleResult();
		} catch(Exception e) {
			return null;
		}
		
		return user;
	}
}
