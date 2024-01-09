package com.accenture.springbootexceptionhandlingwithjunit.repository;

import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);
}
