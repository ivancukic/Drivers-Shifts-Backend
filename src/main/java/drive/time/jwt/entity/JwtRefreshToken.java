package drive.time.jwt.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jwt_refresh_token")
public class JwtRefreshToken {
	
	@Id
	@Column(name = "token_id", nullable = false)
	private String tokenId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	
	@Column(name = "expiration_date", nullable = false)
	private LocalDateTime expirationDate;
	
	
	public JwtRefreshToken() {
		
	}


	public JwtRefreshToken(Integer userId, LocalDateTime createDate, LocalDateTime expirationDate) {
		this.userId = userId;
		this.createDate = createDate;
		this.expirationDate = expirationDate;
	}


	public String getTokenId() {
		return tokenId;
	}


	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}


	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}


	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}


	@Override
	public String toString() {
		return "JwtRefreshToken [tokenId=" + tokenId + ", userId=" + userId + ", createDate=" + createDate
				+ ", expirationDate=" + expirationDate + "]";
	}
	
	

}
