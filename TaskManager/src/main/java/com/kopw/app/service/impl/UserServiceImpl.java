package com.kopw.app.service.impl;

import com.kopw.app.model.dto.UserRegisterDto;
import com.kopw.app.model.entity.Role;
import com.kopw.app.model.entity.UserLogin;
import com.kopw.app.repository.RoleRepository;
import com.kopw.app.repository.UserRepository;
import com.kopw.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserRepository userRepository;

    @Transactional
    @Override
    public UserLogin createUser(UserRegisterDto user) {
        UserLogin existedUser = userRepository.findByUserName(user.getUsername());
        if(Objects.nonNull(existedUser)) {
            throw new UsernameNotFoundException("Invalid username!");
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(user.getUsername());
        userLogin.setEncrytedPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleName(user.getRole());
        if(Objects.isNull(role)){
            role = new Role();
            role.setRoleName(user.getRole());
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        userLogin.setRoles(roles);
        return userRepository.save(userLogin);
    }

    @Override
    public UserLogin findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public boolean isValidAccount(String username, String password) {
        UserLogin existedUser = userRepository.findByUserName(username);
        if (existedUser != null) {
            return passwordEncoder.matches(password, existedUser.getEncrytedPassword());
        }
        return false;
    }
}
