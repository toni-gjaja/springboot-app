package hr.webshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/adminprofile")
    public String getAdminProfile(Model model){

        return "adminprofile";





    }



}
