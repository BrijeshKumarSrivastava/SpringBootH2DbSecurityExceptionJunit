package com.accenture.springbootexceptionhandlingwithjunit.controller;

import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import com.accenture.springbootexceptionhandlingwithjunit.repository.UserInfoRepository;
import com.accenture.springbootexceptionhandlingwithjunit.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = UserInfoControllerTest.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserInfoService userInfoService;

    @Mock
    UserInfoRepository userInfoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    UserInfo userInfo;

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo("me", "me@gmail.com", "2yd#479aaa-c4bb43@78s", "ROLE_USER");
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userInfo);

        when(passwordEncoder.encode(userInfo.getPassword())).thenReturn("2yd#479aaa-c4bb43@78s");
        when(userInfoService.addUser(userInfo)).thenReturn(userInfo);
        this.mockMvc.perform(post("/user/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andReturn();
    }

    @Test
    void getUserByName() throws Exception {
        when(userInfoRepository.findByUsername(userInfo.getUsername()))
                .thenReturn(Optional.ofNullable(userInfo));
        this.mockMvc.perform(get("/user/getUser/" + userInfo.getUsername()))
                .andDo(print()).andReturn();
    }

}
