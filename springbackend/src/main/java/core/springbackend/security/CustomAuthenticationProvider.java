package core.springbackend.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import core.springbackend.roles.entities.Right;
import core.springbackend.users.UserService;
import core.springbackend.users.entities.User;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	@Autowired
	public CustomAuthenticationProvider (PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
//		System.out.println(this.passwordEncoder.encode("1234"));
	}
	
	 @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	  
	        String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        User user = userService.getUserByUserName(name);
	        
	        if (user == null) {
	            throw new BadCredentialsException("1000");
	        }
	        if (passwordEncoder.matches(password, user.getPassword())) {
	            throw new BadCredentialsException("1000");
	        }
	        if (user.getState().getName().equals("Inactive")) {
	            throw new DisabledException("1001");
	        }
	        
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        
	        user.getRoles().forEach(role -> {
	        	role.getRights().forEach(right -> {
	        		authorities.add(new SimpleGrantedAuthority(right.getName()));
	        	});
	        });
	        List<GrantedAuthority> rights = new ArrayList<>();
	        
	        return new UsernamePasswordAuthenticationToken(name, null,  authorities);
	         
	    }
	 
	    @Override
	    public boolean supports(Class<?> authentication) {
	        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    }

}
