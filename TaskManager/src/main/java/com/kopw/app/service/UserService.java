package com.kopw.app.service;

import com.kopw.app.model.dto.UserRegisterDto;
import com.kopw.app.model.entity.UserLogin;

public interface UserService {
    UserLogin createUser(UserRegisterDto user);
    UserLogin findByUsername(String username);
    boolean isValidAccount(String username, String password);
}
