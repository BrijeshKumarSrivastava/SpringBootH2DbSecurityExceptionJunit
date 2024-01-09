package com.accenture.springbootexceptionhandlingwithjunit.repository;

import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;
    UserInfo userInfo;

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo("me", "me@gmail.com", "me", "ROLE_USER");
        userInfoRepository.save(userInfo);
    }

    @AfterEach
    void tearDown() {
        userInfo = null;
        userInfoRepository.deleteAll();
    }
}
