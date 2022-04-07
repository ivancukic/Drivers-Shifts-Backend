package drive.time.jwt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drive.time.jwt.entity.Role;
import drive.time.jwt.entity.User;
import drive.time.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	

	@Autowired
	public UserServiceImpl(@Lazy UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public User saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setCreateTime(LocalDateTime.now());
		
		return userRepository.save(user);
	}
	
	@Override
	public Optional<User> findByUsername(String username){
		
		return userRepository.findByUsername(username);
	}
	
	@Override
	@Transactional
	public void changeRole(Role newRole, String username) {
		
		userRepository.updateUserRole(username, newRole);
	}
	
	@Override
	public List<User> findAllUsers(){
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByID(Integer id) {

		return userRepository.findById(id);
	}
	
	
	
}
