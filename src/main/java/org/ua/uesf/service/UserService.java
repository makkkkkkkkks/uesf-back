package org.ua.uesf.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ua.uesf.model.Role;
import org.ua.uesf.model.User;
import org.ua.uesf.resository.RoleRepository;
import org.ua.uesf.resository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private final EntityManager em;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
 //   private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return (UserDetails) user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUserName(user.getUserName());

        if (userFromDB != null) {
            return false;
        }

        user.setRole(new Role(1L, "ROLE_USER"));
      //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
