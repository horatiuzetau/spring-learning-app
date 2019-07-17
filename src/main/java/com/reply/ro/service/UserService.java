package com.reply.ro.service;


import com.reply.ro.models.Cart;
import com.reply.ro.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;
import java.util.List;

public interface UserService {


//    GET THE CURRENT LOGGED IN USER
    public User getUser(Principal principal);

//    UPDATE
    public User updateUser(User user);

//    CREATE
    public User createUser(User user);

//    GET ALL
    public List<User > getAllUsers();
}
