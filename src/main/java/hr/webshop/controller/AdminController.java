package hr.webshop.controller;

import hr.webshop.entity.Category;
import hr.webshop.entity.Product;
import hr.webshop.entity.Receipt;
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

        if (createOrUpdateProduct(product)){
            model.addAttribute("model", adminService.getAllData());
            return "adminprofile";
        }
        return "error";
    }

    @GetMapping("/aboutreceipt/{receiptId}")
    public String getReceiptDetails(@PathVariable Long receiptId, Model model){

        Optional<Receipt> optionalReceipt = adminService.getReceiptService().getReceiptById(receiptId);
        if (optionalReceipt.isEmpty()){
            return "error";
        }
        Receipt receipt = optionalReceipt.get();
        model.addAttribute("receipt", receipt);
        return "adminreceipt";
    }

    @PostMapping("/createproduct")
    public String createProduct(@ModelAttribute Product product, Model model){

        if (createOrUpdateProduct(product)){
            model.addAttribute("model", adminService.getAllData());
            return "adminprofile";
        }
        return "error";
    }

    private boolean createOrUpdateProduct(Product product) {

        Optional<Category> category = adminService.getCategoryService().getCategoryById(product.getCategory().getId());
        if (category.isEmpty()) {
            return false;
        }
        Category updatedProductCategory = category.get();
        product.setCategory(updatedProductCategory);
        adminService.getProductService().saveProduct(product);
        return true;
    }

    @PostMapping("/createcategory")
    public String createCategory(@ModelAttribute Category category, Model model){
        adminService.getCategoryService().saveCategory(category);
        model.addAttribute("model", adminService.getAllData());
        return "adminprofile";
    }

}
