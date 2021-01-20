package com.codegym.controllers;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "/index";
    }

    @GetMapping("/customer/create")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/customer/save")
    public String save(Customer customer){
        customer.setId((int)(Math.random()*10000));
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/view";
    }

    @GetMapping("/customer/{id}/edit")
    public String showFormEdit(Model model , @PathVariable int id){
        model.addAttribute("customer",customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/customer/edit")
    public String saveEdit(Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/delete")
    public String delete(Customer customer , Model model, @PathVariable int id){
        customerService.remove(id);
        return "redirect:/";
    }


}
