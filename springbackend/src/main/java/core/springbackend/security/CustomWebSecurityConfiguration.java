package core.springbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomWebSecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
//		System.out.println(this.passwordEncoder.encode("1234"));
	}
	
	@Autowired
    private AuthenticationProvider authProvider;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic()
            .and()
            	.authorizeRequests()
//            		.antMatchers("/index.html", "/", "/home", "/login").permitAll()
//            		.antMatchers("/resources/**").permitAll()
//                    .antMatchers("/*.js").permitAll()
		        	.anyRequest().authenticated()
        	.and()
        		.csrf()
        			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        	;
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	    	.ignoring()
//		    	.antMatchers("/api/shared/**")
//		    	.antMatchers("/resources/**", "/api/shared/**", "/static/**", "/index.html", "/css/**", "/js/**", "/img/**", "/icon/**");
	    		.antMatchers("/resources/**" , "/index.html", "/", "/home", "/login", "/*.js", "/*.css", "/favicon.ico")
	    		;
	}	

}
