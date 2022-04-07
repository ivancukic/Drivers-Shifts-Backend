package drive.time.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.time.jwt.entity.User;
import drive.time.jwt.service.AuthenticationService;
import drive.time.jwt.service.JwtRefreshTokenService;
import drive.time.jwt.service.UserService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

	private AuthenticationService authenticationService;
	private UserService userService;
	private JwtRefreshTokenService jwtRefreshTokenService;
	
	
	public AuthenticationController(AuthenticationService authenticationService, UserService userService,
			JwtRefreshTokenService jwtRefreshTokenService) {

		this.authenticationService = authenticationService;
		this.userService = userService;
		this.jwtRefreshTokenService = jwtRefreshTokenService;
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody User user){
		
		if(userService.findByUsername(user.getUsername()).isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user){
		
		return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestParam String token){
		
		return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
	}
	
	
}
