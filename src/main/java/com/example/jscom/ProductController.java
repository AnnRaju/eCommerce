package com.example.jscom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @GetMapping("/products")
    public String listProducts(Model model, HttpSession session) {
        List<Product> productList = productRepository.findAll();
        long cnt =cartItemRepository.count();
        String value = (String) session.getAttribute("user");
        if(value!=null)
            model.addAttribute("user",value);
        else
            return "redirect:/login";
       
        model.addAttribute("products", productList);
        if(cnt>0)
        model.addAttribute("cartSize",cnt);
        return "product-list";
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }
}

