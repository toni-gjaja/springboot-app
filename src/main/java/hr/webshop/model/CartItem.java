package hr.webshop.model;

import hr.webshop.entity.Product;

import java.math.BigDecimal;

public class CartItem {

    Product product;

    int quantity;

    double amount;

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        amount = product.getPrice() * quantity;
    }

}