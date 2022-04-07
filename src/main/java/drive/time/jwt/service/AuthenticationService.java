package drive.time.jwt.service;

import drive.time.jwt.entity.User;

public interface AuthenticationService {

	User signInAndReturnJWT(User signInRequest);

}
