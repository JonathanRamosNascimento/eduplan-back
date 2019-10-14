package com.unievangelica.eduplan.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unievangelica.eduplan.api.entity.User;
import com.unievangelica.eduplan.api.security.jwt.JwtUserFactory;
import com.unievangelica.eduplan.api.service.UserService;

@Service
public class JwtUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    		User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com nome de usuário '%s'.", email));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}