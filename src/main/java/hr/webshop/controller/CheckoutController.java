package hr.webshop.controller;

import hr.webshop.entity.AppUser;
import hr.webshop.entity.Receipt;
import hr.webshop.model.Cart;
import hr.webshop.service.AppUserService;
import hr.webshop.service.CartService;
import hr.webshop.service.ProductService;
import hr.webshop.service.ReceiptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    private final CartService cartService;

    private final AppUserService appUserService;

    private final ReceiptService receiptService;

    private final ProductService productService;
    @Autowired
    public CheckoutController(CartService cartService, AppUserService appUserService, ReceiptService receiptService, ProductService productService) {
        this.cartService = cartService;
        this.appUserService = appUserService;
        this.receiptService = receiptService;
        this.productService = productService;
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(HttpServletRequest request, Model model){
        Cart cart = cartService.getCart(request.getSession());
        AppUser appUser = appUserService.getUserByEmail(getEmailFromSession());
        if (appUser != null){
            model.addAttribute("cart", cart);
            model.addAttribute("user", appUser);
            return "checkout";
        }
        return "error";
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

    @PostMapping("/order")
    public String order(@RequestParam String address, @RequestParam String payment, HttpServletRequest request, Model model){
        Cart cart = cartService.getCart(request.getSession());
        AppUser appUser = appUserService.getUserByEmail(getEmailFromSession());
        if (appUser != null){
            Receipt receipt = new Receipt(cart.getTotalPrice(), payment, appUser, cart.getProducts());
            productService.updateStock(cart);
            receiptService.saveReceipt(receipt);
            model.addAttribute("cart", cart);
            model.addAttribute("receipt", "JWP/R/nr." + receipt.getId().toString());
            model.addAttribute("address", address);
            model.addAttribute("user", appUser);
            request.getSession().removeAttribute("userCart");
            return "receipt";

        }
        return "error";
    }




}
