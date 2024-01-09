package com.accenture.springbootexceptionhandlingwithjunit.service;

import com.accenture.springbootexceptionhandlingwithjunit.config.UserInfoDetails;
import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import com.accenture.springbootexceptionhandlingwithjunit.exception.MissingInputValueException;
import com.accenture.springbootexceptionhandlingwithjunit.exception.UserInfoNotFoundException;
import com.accenture.springbootexceptionhandlingwithjunit.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoService implements UserDetailsService {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo addUser(UserInfo userInfo) {
        if(userInfo.getUsername().isEmpty() || userInfo.getPassword().isEmpty() || userInfo.getEmail().isEmpty() || userInfo.getRoles().isEmpty()) {
            LOGGER.info("Missing some input so could not add user with: username={} ", userInfo.getUsername());
            throw new MissingInputValueException("Missing input value, please provide it.");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        LOGGER.info("User added with: username={} ", userInfo.getUsername());
        return repository.save(userInfo);

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(repository.findByUsername(username).isEmpty()) {
            LOGGER.info("No User found with: username={} ", username);
            throw new UserInfoNotFoundException("Requested User does not exist");
        }
        Optional<UserInfo> userInfo = repository.findByUsername(username);
        return userInfo.map(UserInfoDetails::new)
               .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}

