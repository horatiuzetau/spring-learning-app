package com.reply.ro.service.impl;


import com.reply.ro.models.Cart;
import com.reply.ro.models.Role;
import com.reply.ro.models.SessionCart;
import com.reply.ro.models.User;
import com.reply.ro.repository.RoleRepository;
import com.reply.ro.repository.UserRepository;
import com.reply.ro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    @Override
    public User getUser(Principal principal){
        return userRepository.findUserByUsername(principal.getName()).get();
    }

    @Override
    public User updateUser(User user){
        Optional<User > userFromDB = userRepository.findById(user.getId());

        if(userFromDB.isPresent()){
            User u = userFromDB.get();

            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setCart(u.getCart());
            user.setProductsAdded(u.getProductsAdded());

            if(user.getRoles().isEmpty()){
                Role role = roleRepository.findByName("USER");

                user.addRole(role);
            }

            return userRepository.save(user);
        }else{

            return null;
        }
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

//        Setting roles
        if(user.getRoles().isEmpty()){
            user.addRole("USER");
        }

//        Save it
        return userRepository.save(user);
    }

    @Override
    public List<User > getAllUsers(){
        return userRepository.findAll();
    }


}
