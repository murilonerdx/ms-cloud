package com.murilo.hroauth.services;

import com.murilo.hroauth.entities.User;
import com.murilo.hroauth.feignclientes.UserFeignClientes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClientes userFeignClientes;

    public User findByEmail(String email) throws IllegalAccessException {
        User user = userFeignClientes.findByEmail(email).getBody();
        if(user == null){
            logger.error("Email not found: " + email);
            throw new IllegalAccessException("Email not found");
        }
        logger.info("Email found: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userFeignClientes.findByEmail(s).getBody();
        if(user == null){
            logger.error("Username not found: " + s);
            throw new UsernameNotFoundException("Username not found");
        }
        logger.info("Username found: " + s);
        return user;
    }
}
