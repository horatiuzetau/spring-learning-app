package com.reply.ro.service.impl;


import com.reply.ro.models.Cart;
import com.reply.ro.models.User;
import com.reply.ro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user){
        Cart cart = user.getCart();
        cart.setOwner(user);
        user.setCart(cart);


        return userRepository.save(user);
    }

    public List<User > getAllUsers(){
        return userRepository.findAll();
    }


}
