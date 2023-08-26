package hr.webshop.controller;

import hr.webshop.entity.Product;
import hr.webshop.model.AdminProfileModel;
import hr.webshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AppUserService appUserService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ReceiptService receiptService;
    private final UserLogService userLogService;

    AdminProfileModel adminProfileModel;

    @Autowired
    public AdminController(AppUserService appUserService, CategoryService categoryService, ProductService productService, ReceiptService receiptService, UserLogService userLogService) {
        this.appUserService = appUserService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.receiptService = receiptService;
        this.userLogService = userLogService;

        adminProfileModel = new AdminProfileModel(
                appUserService.getAllUsers(),
                categoryService.getAllCategories(),
                productService.getAllProducts(),
                receiptService.getAllReceipts(),
                userLogService.getAllLogs()
        );

    }

    @GetMapping("/adminprofile")
    public String getAdminProfile(Model model){

        model.addAttribute("model", adminProfileModel);
        return "adminprofile";

    }

    @PostMapping("/deleteproduct/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.removeProduct(productId);
        //adminProfileModel.getProducts().removeIf(product -> product.getId().equals(id));
        //model.addAttribute("model", adminProfileModel);
        return "adminprofile";
    }

    @GetMapping("/aboutproduct/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isEmpty()){
            return "error";
        }
        Product modelProduct = product.get();
        model.addAttribute("product", modelProduct);
        return "adminproduct";
    }

    @PostMapping("/updateproduct")
    public String updateProduct(@ModelAttribute("product") Product product){

        productService.saveProduct(product);
        return "adminprofile";
    }

}
