package com.reply.ro.mvc;


import com.reply.ro.models.Cart;
import com.reply.ro.models.Product;
import com.reply.ro.models.SessionCart;
import com.reply.ro.repository.CartRepository;
import com.reply.ro.repository.ProductRepository;
import com.reply.ro.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@Scope("request")
public class CartController {

    @Autowired private CartServiceImpl cartService;

    @GetMapping("/cart")
    public Cart getCart(Principal principal){
        return  cartService.getCart(principal);
    }

    @PutMapping("/cart/add/{id_prod}")
    public Cart addProductToCart(@PathVariable Long id_prod, Principal principal){
        return cartService.addItemToCart(id_prod, principal);
    }


//    DEBUGGING

    @GetMapping("/manage/carts/all")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

}
