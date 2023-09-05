package hr.webshop.model;

import hr.webshop.entity.Product;
import lombok.Data;

import java.text.DecimalFormat;

@Data
public class CartItem {

    private Product product;

    private int quantity;

    private double amount;

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        amount = product.getPrice() * quantity;
    }

    public String getFormattedAmount(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(amount);
    }


}
