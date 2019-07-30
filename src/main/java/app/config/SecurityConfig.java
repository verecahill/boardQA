package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.security.CustomAuthenticationFailure;
import app.security.CustomAuthenticationSuccess;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

/*	인증방식*/
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication()
//		.withUser("user").password("user").authorities("USER")
//		.and()
//		.withUser("admin").password("admin").authorities("USER", "ADMIN");
		
//		auth.jdbcAuthentication().dataSource(dataSource())
//		usersByu.;
		
	}
	
	
	/*시큐어 패턴등록*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().ignoringAntMatchers("/h2-console/**");
	    http.headers().frameOptions().sameOrigin();

		http.authorizeRequests()
		.antMatchers("/resources/**","/loginError","/registration","/h2-console/**").permitAll()
		.antMatchers("/boards/**").hasAuthority("USER")
//		.anyRequest().authenticated()
		.and()
		.formLogin()
		.usernameParameter("userId")
		.passwordParameter("password")
		.loginPage("/users/login")
		.loginProcessingUrl("/login")
		.defaultSuccessUrl("/")
		.successHandler(new CustomAuthenticationSuccess())
		.failureHandler(new CustomAuthenticationFailure())
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.csrf().disable()
		.exceptionHandling()
		.accessDeniedPage("/403");
//		.httpBasic()
		// :h2-console connection
		
			
	}
	

}
