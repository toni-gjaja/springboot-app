package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import hr.webshop.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @GetMapping("/userlogout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.clearContext();
        }
        return "/login";
    }
}
