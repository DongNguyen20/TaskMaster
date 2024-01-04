package com.kopw.app.repository;

import com.kopw.app.model.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserLogin, Long> {
    @Query("Select  u from UserLogin u Where u.userName = :username")
    UserLogin findByUserName(@Param("username") String username);
}
