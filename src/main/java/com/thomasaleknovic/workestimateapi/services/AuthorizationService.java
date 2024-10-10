package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.exceptions.User.UserNotFoundException;
import com.thomasaleknovic.workestimateapi.models.User;
import com.thomasaleknovic.workestimateapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        } else {
            return user;
        }
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
