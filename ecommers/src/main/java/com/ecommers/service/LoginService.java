package com.ecommers.service;

import com.ecommers.domain.LoginDomain;
import com.ecommers.domain.LoginResponseDomain;
import com.ecommers.jwt.JwtUtil;
import com.ecommers.model.Users;
import com.ecommers.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDomain createAuthentication(LoginDomain loginDomain) {

        Users     user=usersRepository.findByMobileNumberOrEmailId(loginDomain.getEmailIdOrPhoneNumner()).orElseThrow(()->  new RuntimeException("invalid creadentials"));
        if(!passwordEncoder.matches(loginDomain.getPassword(), user.getPassword())){
            throw new RuntimeException("invalid creadentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        LoginResponseDomain loginResponseDomain=new LoginResponseDomain();
        loginResponseDomain.setToken(token);
        loginResponseDomain.setUsername(user.getUsername());
        loginResponseDomain.setUserRole(user.getUserRole());
        loginResponseDomain.setEmail(user.getEmail());

        return loginResponseDomain;
    }
}
