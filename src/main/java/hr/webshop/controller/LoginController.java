package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import hr.webshop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final AppUserService service;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(AppUserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.saveAppUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new AppUser());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession();
        if (session != null){
            session.invalidate();
        }
        return "/login";
    }

}
