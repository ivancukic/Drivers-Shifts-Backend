package drive.time.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import drive.time.jwt.entity.Role;
import drive.time.jwt.security.jwt.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Autowired
	public SecurityConfig(@Lazy CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
				.antMatchers("/api/authentication/**").permitAll()
				.antMatchers("/api/admin/**").hasRole(Role.ADMIN.name())
				.antMatchers("/api/category/**").permitAll()
				.antMatchers("/api/driver/**").permitAll()
				.antMatchers("/api/schedule/**").permitAll()
				.antMatchers("/api/line/**").permitAll()
				.antMatchers("/api/shift/**").permitAll()
//				.antMatchers("/api/category/all").permitAll()
//				.antMatchers("/api/category/get/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/category/save/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/category/update/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/category/delete/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/driver/all").permitAll()
//				.antMatchers("/api/driver/get/**").permitAll()
//				.antMatchers("/api/driver/save").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/driver/update/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/driver/delete/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/schedule/all").permitAll()
//				.antMatchers("/api/schedule/get/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/line/all").permitAll()
//				.antMatchers("/api/line/get/**").permitAll()
//				.antMatchers("/api/line/save").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/line/update/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/line/delete/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/shift/all").permitAll()
//				.antMatchers("/api/shift/get/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/shift/updateOne/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/shift/updateTwo/**").hasRole(Role.ADMIN.name())
//				.antMatchers("/api/shift/updateThree/**").hasRole(Role.ADMIN.name())
				.anyRequest().authenticated();
		
		
		http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() {
		
		return new JwtAuthorizationFilter();
	} 
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
		
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*");
			}
		
		};
	}
	
}
