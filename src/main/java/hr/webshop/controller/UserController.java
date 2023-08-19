package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String getLoginPage(){
        return "profile";
    }

}
