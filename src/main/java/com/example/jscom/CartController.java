package com.example.jscom;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/cart")
    public String addToCart(@RequestParam Long productId) {
        cartService.addToCart(productId);
        return "Product added to cart";
    }
    @GetMapping("/cart")
    public String viewCart(Model model) {
    List<CartItem> cartItems = cartItemRepository.findAll();
    List<CartItemWithProduct> cartItemsWithProduct = cartItems.stream()
           .map(cartItem -> {
                Product product = productRepository.findById(cartItem.getProductId()).orElseThrow();
                return new CartItemWithProduct(cartItem, product);
            })
           .collect(Collectors.toList());
    double totalAmount = cartItemsWithProduct.stream()
           .mapToDouble(cartItem -> cartItem.getProduct().getPrice())
           .sum();
    model.addAttribute("cartItems", cartItemsWithProduct);
    model.addAttribute("totalAmount", totalAmount);
    return "cart";
    }   
    @PostMapping("/clear-cart")
    public @ResponseBody String clearCart() {
        cartService.clearCart();
        return "Cart cleared";
    }
}
