package hr.webshop.controller;

import hr.webshop.entity.Product;
import hr.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = service.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/details/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        Optional<Product> product = service.getProductById(productId);
        if (product.isPresent()){
            Product modelProduct = product.get();
            model.addAttribute("product", modelProduct);
        }
        else {
            return "error";
        }
        return "product";
    }


}
