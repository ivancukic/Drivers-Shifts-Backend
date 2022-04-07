package drive.time.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import drive.time.jwt.entity.User;
import drive.time.jwt.security.UserPrincipal;
import drive.time.jwt.security.jwt.JwtProvider;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;
	private JwtRefreshTokenService jwtRefreshTokenService; 
	
	
	@Autowired
	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
			JwtRefreshTokenService jwtRefreshTokenService) {
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.jwtRefreshTokenService = jwtRefreshTokenService;
	}


	@Override
	public User signInAndReturnJWT(User signInRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), 
														signInRequest.getPassword())
		);
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String jwt = jwtProvider.generateToken(userPrincipal);
		
		User signInUser = userPrincipal.getUser();
		signInUser.setAccessToken(jwt);
		signInUser.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());
		
		return signInUser;
	}


	


	
	
}
