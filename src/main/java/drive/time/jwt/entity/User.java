package drive.time.jwt.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	
	@Transient
	private String accessToken;
	
	@Transient
	private String refreshToken;
	
	public User() {
		
	}

	

	public User(String username, String password, String name, LocalDateTime createTime, Role role, String accessToken,
			String refreshToken) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.createTime = createTime;
		this.role = role;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", createTime=" + createTime + ", role=" + role + "]";
	}
	
	
	
}
