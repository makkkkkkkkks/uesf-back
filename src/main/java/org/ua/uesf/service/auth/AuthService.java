package org.ua.uesf.service.auth;

import org.ua.uesf.model.dto.user.AuthRequest;
import org.ua.uesf.model.dto.user.AuthResponse;
import org.ua.uesf.model.dto.user.RegistrationDto;

public interface AuthService {

    AuthResponse register(RegistrationDto registrationDto);

    AuthResponse auth(AuthRequest authRequest);

    void login();

    void forgotPassword();

    void deleteAccount();

    void updateAccount();

}
