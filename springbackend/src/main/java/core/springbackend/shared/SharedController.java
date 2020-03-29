package core.springbackend.shared;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.springbackend.shared.entities.State;

@RestController
@RequestMapping(value = "/api/shared")
@CrossOrigin(origins = "http://localhost:4200")
public class SharedController {
	
	private SharedService sharedService;
	
	@Autowired
	public SharedController (SharedService ss) {
		sharedService = ss;
	}
	
	@GetMapping("/getAllStates")
	public List<State> getAllStates(){
		return sharedService.getAllStates();
	}
	

}
