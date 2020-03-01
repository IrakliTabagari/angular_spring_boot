package core.springbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SpringbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbackendApplication.class, args);
	}
	
	@GetMapping("/home")
	public String hello() {
		return "Hello World";
	}

}
