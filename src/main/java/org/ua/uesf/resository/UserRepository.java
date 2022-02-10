package org.ua.uesf.resository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
