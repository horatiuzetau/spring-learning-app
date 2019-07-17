package com.reply.ro.service.impl;


import com.reply.ro.models.Cart;
import com.reply.ro.models.SessionCart;
import com.reply.ro.models.User;
import com.reply.ro.repository.UserRepository;
import com.reply.ro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;

    @Override
    public User getUser(Principal principal){
        return userRepository.findUserByUsername(principal.getName()).get();
    }

//  REVINO AICI
    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user){

//        Create a cart for the new user
        Cart cart = new Cart();

//        Set its owner
        cart.setOwner(user);

//        Set its name
        cart.setName(user.getUsername());

//        Set the cart to the user
        user.setCart(cart);

//        Encode the password
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);

//        Save it
        return userRepository.save(user);
    }

    @Override
    public List<User > getAllUsers(){
        return userRepository.findAll();
    }


}
