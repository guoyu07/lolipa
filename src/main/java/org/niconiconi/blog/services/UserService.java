package org.niconiconi.blog.services;

import org.niconiconi.blog.models.User;
import org.niconiconi.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/9/23.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User update(String username, User user) {
        User sUser = userRepository.findByUsername(username);
        sUser.setUsername(user.getUsername());
        if (user.getPassword().length() > 0) {
            PasswordEncoder passwordEncoder = new StandardPasswordEncoder("53cr3t");
            sUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(sUser);
    }
}
