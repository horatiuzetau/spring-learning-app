package com.reply.ro.service;

import com.reply.ro.models.Cart;
import com.reply.ro.models.Product;
import com.reply.ro.models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CartService {

//    ADD ITEM TO CART
    Cart addItemToCart(Long id_prod, Principal principal);

//    GET CURRENT CART -> PRINCIPAL OR SESSION
    Cart getCart(Principal principal);

//    GET ALL CARTS
    List<Cart> getAllCarts();

}
