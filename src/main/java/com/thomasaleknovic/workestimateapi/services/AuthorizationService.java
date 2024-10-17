package com.thomasaleknovic.workestimateapi.services;

import com.thomasaleknovic.workestimateapi.exceptions.User.UserNotFoundException;
import com.thomasaleknovic.workestimateapi.exceptions.User.UsernameAlreadyExistsException;
import com.thomasaleknovic.workestimateapi.models.User;
import com.thomasaleknovic.workestimateapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser (UserDetails user, String username) {
        User userToUpdate = userRepository.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);

        if(userRepository.findByUsername(username).isEmpty()){
            userToUpdate.setUsername(username);
            return userRepository.save(userToUpdate);
        } else throw new UsernameAlreadyExistsException();
    }

}
