package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import hr.webshop.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final AppUserService service;

    @Autowired
    public LoginController(AppUserService service) {

        this.service = service;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") AppUser user){

        boolean email = service.checkExistingEmail(user.getEmail());
        if (email){
            return "error";
        }
        service.saveAppUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new AppUser());
        return "login";
    }

    @GetMapping("/adminlogin")
    public String getAdminLoginPage(Model model){
        model.addAttribute("user", new AppUser());
        return "adminlogin";
    }

}
