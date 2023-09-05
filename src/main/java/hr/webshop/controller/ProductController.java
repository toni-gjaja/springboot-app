package hr.webshop.controller;

import hr.webshop.entity.Product;
import hr.webshop.model.Cart;
import hr.webshop.service.CartService;
import hr.webshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService service;
    private final CartService cartService;
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService service, CartService cartService, ProductService productService) {
        this.service = service;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = service.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model) {
        Optional<Product> product = service.getProductById(productId);
        if (product.isEmpty()){
            return "error";
        }
        Product modelProduct = product.get();
        model.addAttribute("product", modelProduct);
        return "product";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpServletRequest request){
        Cart cart = cartService.getCart(request.getSession());
        model.addAttribute("cart", cart);
        return "cart";

    }

    @PostMapping("/updateitem")
    public String updateCart(@RequestParam Long productId, @RequestParam int quantity, HttpServletRequest request, Model model){
        Cart cart = cartService.getCart(request.getSession());
        Product product = getProductById(productId);
        if (product != null){
            cart = cartService.updateItem(cart, product, quantity, request.getSession());
            model.addAttribute("cart", cart);
            return "cart";
        }
        return "error";
    }

    @PostMapping("/additem")
    public String addItem(@RequestParam Long productId, @RequestParam int quantity, HttpServletRequest request, Model model){
        Cart cart = cartService.getCart(request.getSession());
        Product product = getProductById(productId);
        if (product != null){
            cart = cartService.addItem(cart, product, quantity, request.getSession());
            model.addAttribute("cart", cart);
            return "cart";
        }
        return "error";
    }

    @PostMapping("/deleteitem")
    public String deleteItem(@RequestParam Long productId, HttpServletRequest request, Model model){
        Cart cart = cartService.getCart(request.getSession());
        Product product = getProductById(productId);
        if (product != null){
            cart = cartService.removeItem(cart, product, request.getSession());
            model.addAttribute("cart", cart);
            return "cart";
        }
        return "error";
    }

    private Product getProductById(Long id){
        Optional<Product> optionalProduct = productService.getProductById(id);
        return optionalProduct.orElse(null);
    }

}
