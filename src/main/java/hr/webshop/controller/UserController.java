package hr.webshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('USER')")
public class UserController {

    @GetMapping("/profile")
    public String getUserProfile(){
        return "profile";
    }

}
