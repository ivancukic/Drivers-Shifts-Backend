package drive.time.jwt.service;

import drive.time.jwt.entity.JwtRefreshToken;
import drive.time.jwt.entity.User;

public interface JwtRefreshTokenService {

	JwtRefreshToken createRefreshToken(Integer userId);

	User generateAccessTokenFromRefreshToken(String refreshTokenId);

}
