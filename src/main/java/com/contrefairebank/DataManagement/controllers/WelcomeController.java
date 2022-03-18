package com.contrefairebank.DataManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class WelcomeController {
    
    @GetMapping(value = "/welcome")
    public String welcome(@RequestParam(name = "customer", required = false, defaultValue = "(customer)") String customer, Model model) {
        model.addAttribute("customer", customer);
        return "welcome";
    }
}
