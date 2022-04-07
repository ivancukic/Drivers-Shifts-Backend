package drive.time.jwt.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.User;
import drive.time.jwt.service.UserService;
import drive.time.jwt.utils.SecurityUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	private UserService userService;
	
	@Autowired
	public CustomUserDetailsService(@Lazy UserService userService) {
		this.userService = userService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findByUsername(username)
							   .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));
		
		
		// it can be done with builder() annotation which is Lombok
		return new UserPrincipal(user.getId(), 
								 user.getUsername(), 
								 user.getPassword(), 
								 user, 
								 authorities);
		
	}

	
	
}
