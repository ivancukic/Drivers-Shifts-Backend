package drive.time.jwt.service;

import java.util.List;
import java.util.Optional;

import drive.time.jwt.entity.Role;
import drive.time.jwt.entity.User;

public interface UserService {

	User saveUser(User user);

	Optional<User> findByUsername(String username);

	void changeRole(Role newRole, String username);

	List<User> findAllUsers();
	
	Optional<User> findByID(Integer id);

}
