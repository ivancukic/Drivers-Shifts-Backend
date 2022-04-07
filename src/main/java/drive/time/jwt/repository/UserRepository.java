package drive.time.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import drive.time.jwt.entity.Role;
import drive.time.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("UPDATE User  SET role = :role WHERE username = :username")
	void updateUserRole(@Param ("username") String username, @Param("role") Role role); 
}
