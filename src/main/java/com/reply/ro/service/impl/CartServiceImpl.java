package com.reply.ro.service.impl;

import com.reply.ro.models.Cart;
import com.reply.ro.models.Product;
import com.reply.ro.models.SessionCart;
import com.reply.ro.models.User;
import com.reply.ro.repository.CartRepository;
import com.reply.ro.repository.ProductRepository;
import com.reply.ro.repository.UserRepository;
import com.reply.ro.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Scope("request")
public class CartServiceImpl implements CartService {

    @Autowired private ProductRepository productRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private CartRepository cartRepository;

    @Autowired private SessionCart sessionCart;


    public List<Cart > getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart addItemToCart(Long id_prod, Principal principal){

        Optional<Product > product = productRepository.findById(id_prod);

        if(!product.isPresent())
            return null;

//        User logged in
        if(principal != null){
            //It's impossible not to exist
            User user = userRepository.findUserByUsername(principal.getName()).get();

            Cart cart = user.getCart();

            if(!cart.getProducts().contains(product.get())){
                product.get().addCart(cart);
                cart.addProduct(product.get());
            }

            return cartRepository.save(cart);
        }else{
//        User not logged in -> Using sessionCart

            sessionCart.addProduct(product.get());

            return sessionCart;
        }
    }

    public Cart getCart(Principal principal){

        if(principal != null){
            //Direct get because we know it exists
            User user = userRepository.findUserByUsername(principal.getName()).get();

            return user.getCart();
        }else{
            return sessionCart;
        }

    }

}
