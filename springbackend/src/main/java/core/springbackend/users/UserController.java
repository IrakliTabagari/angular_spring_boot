package core.springbackend.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.springbackend.shared.SharedService;
import core.springbackend.users.entities.User;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private SharedService sharedService;
	private UserService userService;
	
	@Autowired
	public UserController (SharedService sharedService,
			UserService userService) {
		this.sharedService = sharedService;
		this.userService = userService;
	}
	
	@GetMapping("getAllUsers")
	@PreAuthorize("hasAuthority('getAllUsers')")
	public List<User> getAllUsers(){
		List<User> users = userService.getAllUsers();
		
		return users;
	}

}
