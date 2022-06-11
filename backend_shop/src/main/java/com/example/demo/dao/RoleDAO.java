package com.example.demo.dao;



import  com.example.demo.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO extends DAO{
    //getting role by id
    public Role findById(int id) {
        return getSession().get(Role.class, id);//finding role
    }
}
