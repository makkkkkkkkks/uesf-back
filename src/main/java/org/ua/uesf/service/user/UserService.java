package org.ua.uesf.service.user;

import org.ua.uesf.model.User;

public interface UserService {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

}
