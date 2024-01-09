package com.accenture.springbootexceptionhandlingwithjunit.service;

import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import com.accenture.springbootexceptionhandlingwithjunit.repository.UserInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserInfoServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;
    @InjectMocks
    private UserInfoService userInfoService;
    @Mock
    private PasswordEncoder passwordEncoder;
    AutoCloseable autoCloseable;
    UserInfo userInfo;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userInfo = new UserInfo(101, "me", "me@gmail.com", "2yd#479aaa-c4bb43@78s", "ROLE_USER");
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testAddUser() {
        mock(UserInfo.class);
        mock(UserInfoRepository.class);
        when(passwordEncoder.encode(userInfo.getPassword())).thenReturn("xxxyyyzzz222");
        when(userInfoRepository.save(userInfo)).thenReturn(userInfo);
        assertThat(userInfoService.addUser(userInfo)).isEqualTo(userInfo);

    }

    @Test
    void testFindByUsername() {
        mock(UserInfo.class);
        mock(UserInfoRepository.class);

        when(userInfoRepository.findByUsername(userInfo.getUsername())).
                thenReturn(Optional.of(userInfo));

        assertThat(userInfoService.loadUserByUsername(userInfo.getUsername())
                .equals(userInfo.getUsername()));
    }

}
