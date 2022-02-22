package org.ua.uesf.service.user.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ua.uesf.model.User;
import org.ua.uesf.resository.UserRepository;
import org.ua.uesf.service.user.UserService;

@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public final User findByLogin(String login) {
        return userRepository.findByUserName(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
