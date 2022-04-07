package drive.time.jwt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import drive.time.jwt.entity.Role;
import drive.time.jwt.entity.User;
import drive.time.jwt.security.UserPrincipal;
import drive.time.jwt.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping("/change/{role}")
	public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role){
		
		userService.changeRole(role, userPrincipal.getUsername());
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/get/{id}")
	public @ResponseBody ResponseEntity<User> get(@PathVariable Integer id) {
		
		Optional<User> user = userService.findByID(id);
		
		if(user.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	
}
