package drive.time.jwt.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import drive.time.jwt.entity.User;


//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	transient private String password;
	transient private User user; // only for login operations, i don't use it for JWT
	private Set<GrantedAuthority> authorities;
	
	public UserPrincipal() {

	}
	

	public UserPrincipal(Integer id, String username, Set<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.authorities = authorities;
	}	

	public UserPrincipal(Integer id, String username, String password, Set<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public UserPrincipal(Integer id, String username, String password, User user, Set<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	

}
