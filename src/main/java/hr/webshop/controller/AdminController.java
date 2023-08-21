package hr.webshop.controller;


import hr.webshop.entity.AppUser;
import hr.webshop.entity.Category;
import hr.webshop.model.AdminProfileModel;
import hr.webshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AppUserService appUserService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ReceiptService receiptService;
    private final UserLogService userLogService;

    @Autowired
    public AdminController(AppUserService appUserService, CategoryService categoryService, ProductService productService, ReceiptService receiptService, UserLogService userLogService) {
        this.appUserService = appUserService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.receiptService = receiptService;
        this.userLogService = userLogService;
    }


    @GetMapping("/adminprofile")
    public String getAdminProfile(Model model){

        AdminProfileModel adminProfileModel = new AdminProfileModel(
                appUserService.getAllUsers(),
                categoryService.getAllCategories(),
                productService.getAllProducts(),
                receiptService.getAllReceipts(),
                userLogService.getAllLogs()
        );

        model.addAttribute("model", adminProfileModel);

        return "adminprofile";

    }

}
