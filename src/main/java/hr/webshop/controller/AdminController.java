package hr.webshop.controller;

import hr.webshop.entity.Category;
import hr.webshop.entity.Product;
import hr.webshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {

        this.adminService = adminService;
    }

    @GetMapping("/adminprofile")
    public String getAdminProfile(Model model) {
        model.addAttribute("model", adminService.getAllData());
        return "adminprofile";

    }

    @PostMapping("/deleteproduct")
    public String deleteProduct(@RequestParam Long id, Model model) {
        adminService.getProductService().removeProduct(id);
        model.addAttribute("model", adminService.getAllData());
        return "adminprofile";
    }

    @GetMapping("/aboutproduct/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        Optional<Product> product = adminService.getProductService().getProductById(productId);
        if (product.isEmpty()) {
            return "error";
        }
        Product modelProduct = product.get();
        model.addAttribute("product", modelProduct);
        return "adminproduct";
    }

    @PostMapping("/updateproduct")
    public String updateProduct(@ModelAttribute Product product, Model model) {

        Optional<Category> category = adminService.getCategoryService().getCategoryById(product.getCategory().getId());
        if (category.isEmpty()) {
            return "error";
        }
        Category updatedProductCategory = category.get();
        product.setCategory(updatedProductCategory);

        adminService.getProductService().saveProduct(product);

        model.addAttribute("model", adminService.getAllData());
        return "adminprofile";
    }

}
