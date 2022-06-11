package com.example.demo.service;


import com.example.demo.dao.UserDAO;
import com.example.demo.model.CustomerUserDetail;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUDS implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= Optional.ofNullable(userDAO.findUserByEmail(email));
        //user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return user.map(CustomerUserDetail::new).get();
    }
}
