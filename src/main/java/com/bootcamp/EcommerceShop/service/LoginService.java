package com.bootcamp.EcommerceShop.service;


import com.bootcamp.EcommerceShop.model.Role;
import com.bootcamp.EcommerceShop.model.Users;
import com.bootcamp.EcommerceShop.repository.RoleRepository;
import com.bootcamp.EcommerceShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @Autowired
    public LoginService(BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public String register(Users users, HttpServletRequest httpServletRequest) throws ServletException {
        String redirect;
        String password = users.getPassword();
        users.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        if(users.getRole().equalsIgnoreCase("Buyer")){
            roles.add(roleRepository.findById(2).get());
            redirect = "/login";
        } else {
            roles.add(roleRepository.findById(1).get());
            redirect = "/login";
        }

        users.setRoles(roles);
        userRepository.save(users);
        httpServletRequest.login(users.getEmail(), password);
        return redirect;
    }
}