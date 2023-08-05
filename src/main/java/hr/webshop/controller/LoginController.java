package hr.webshop.controller;

import hr.webshop.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access")
public class LoginController {

    private final AppUserService service;

    @Autowired
    public LoginController(AppUserService service) {
        this.service = service;
    }
    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }
}
