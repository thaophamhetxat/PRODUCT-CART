package com.codegym.controller;

import moduls.Cart;
import moduls.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    IProductService iProductService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

    @GetMapping("shop")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("list", iProductService.findAll());
        return modelAndView;
    }


    @GetMapping("add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error";
        }
        if (action.equals("show")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }
        cart.addProduct(productOptional.get());
        return "redirect:/shop";
    }




    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        return  modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Product product){
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/shop");
        return  modelAndView;
    }


}
