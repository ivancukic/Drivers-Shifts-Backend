package drive.time.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import drive.time.jwt.entity.JwtRefreshToken;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String> {

}
