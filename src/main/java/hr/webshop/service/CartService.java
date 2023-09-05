package hr.webshop.service;

import hr.webshop.entity.Product;
import hr.webshop.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private static final String SESSION_KEY = "userCart";

    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute(SESSION_KEY);
        if (cart == null){
            cart = new Cart();
            session.setAttribute(SESSION_KEY, cart);
        }
        return cart;
    }

    public Cart addItem(Cart cart, Product product, int quantity, HttpSession session) {
        cart.addItem(product, quantity);
        session.setAttribute(SESSION_KEY, cart);
        return cart;
    }

    public Cart removeItem(Cart cart, Product product, HttpSession session){
        cart.removeItem(product);
        session.setAttribute(SESSION_KEY, cart);
        return cart;
    }

    public Cart updateItem(Cart cart, Product product, int quantity, HttpSession session){
        cart.updateItem(product, quantity);
        session.setAttribute(SESSION_KEY, cart);
        return cart;
    }

}
