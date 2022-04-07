package drive.time.jwt.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.JwtRefreshToken;
import drive.time.jwt.entity.User;
import drive.time.jwt.repository.JwtRefreshTokenRepository;
import drive.time.jwt.repository.UserRepository;
import drive.time.jwt.security.UserPrincipal;
import drive.time.jwt.security.jwt.JwtProvider;
import drive.time.jwt.utils.SecurityUtils;

@Service
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {

	@Value("${app.jwt.refresh-expiration-in-ms}")
	private Long REFRESH_EXPIRATION_IN_MS;
	
	private JwtRefreshTokenRepository jwtRefreshTokenRepository;
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	@Autowired
	public JwtRefreshTokenServiceImpl(JwtRefreshTokenRepository jwtRefreshTokenRepository,
			UserRepository userRepository, JwtProvider jwtProvider) {
		this.jwtRefreshTokenRepository = jwtRefreshTokenRepository;
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	
	@Override
	public JwtRefreshToken createRefreshToken(Integer userId) {
		
		JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();
		
		jwtRefreshToken.setTokenId(UUID.randomUUID().toString());
		jwtRefreshToken.setUserId(userId);
		jwtRefreshToken.setCreateDate(LocalDateTime.now());
		jwtRefreshToken.setExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));
		
		return jwtRefreshTokenRepository.save(jwtRefreshToken);
	}
	
	@Override
	public User generateAccessTokenFromRefreshToken(String refreshTokenId) {
		
		JwtRefreshToken jwtRefreshToken = jwtRefreshTokenRepository.findById(refreshTokenId).orElseThrow();
		
		if(jwtRefreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
			
			throw new RuntimeException("JWT refresh token is not valid.");
		}
		
		User user = userRepository.findById(jwtRefreshToken.getUserId()).orElseThrow();
		
		UserPrincipal userPrincipal = new UserPrincipal(user.getId(), 
														user.getUsername(), 
														user.getPassword(), 
														Set.of(SecurityUtils.convertToAuthority(user.getRole().name())));
		
//		UserPrincipal userPrincipal = UserPrincipal.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorities(Set.of(SecurityUtils.convertToAuthority(user.getRole().name())))
//                .build();

		
		
		String accessToken = jwtProvider.generateToken(userPrincipal);
		
		user.setAccessToken(accessToken);
		user.setRefreshToken(refreshTokenId);
		
		return user;
	}
	
	
}
