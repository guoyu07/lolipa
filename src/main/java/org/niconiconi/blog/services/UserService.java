package org.niconiconi.blog.services;

import org.niconiconi.blog.models.User;
import org.niconiconi.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by Volio on 2016/9/23.
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User update(String username, User user) {
        User sUser = userRepository.findByUsername(username);
        sUser.setUsername(user.getUsername());
        if (user.getPassword().length() > 0) {
            PasswordEncoder passwordEncoder = new StandardPasswordEncoder("53cr3t");
            sUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(sUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("user not exist");
        return createSpringUser(user);
    }

    private org.springframework.security.core.userdetails.User createSpringUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(createAuthority()));
    }

    private GrantedAuthority createAuthority() {
        return new SimpleGrantedAuthority("ADMIN");
    }
}
