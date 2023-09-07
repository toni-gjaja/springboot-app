package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import hr.webshop.service.AppUserService;
import hr.webshop.service.ReceiptService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final ReceiptService receiptService;

    private final AppUserService appUserService;

    public UserController(ReceiptService receiptService, AppUserService appUserService) {
        this.receiptService = receiptService;
        this.appUserService = appUserService;
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model){
        AppUser appUser = appUserService.getUserByEmail(getEmailFromSession());
        model.addAttribute("receipts", receiptService.getAllByUserId(appUser.getId()));
        return "profile";
    }

    private String getEmailFromSession() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else {
            return "";
        }
    }

}
