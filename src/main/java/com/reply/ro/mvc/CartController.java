package com.reply.ro.mvc;


import com.reply.ro.models.Cart;
import com.reply.ro.models.Product;
import com.reply.ro.repository.CartRepository;
import com.reply.ro.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    @PutMapping("/{id_cart}-{id_prod}")
    public Cart addProductsToCart(@PathVariable Long id_cart, @PathVariable Long id_prod){
        Cart cart = cartRepository.findById(id_cart).get();

        Product product = productRepository.findById(id_prod).get();

        product.addCart(cart);
        cart.addProduct(product);

        return cartRepository.save(cart);
    }


}
