package hr.webshop.model;

import hr.webshop.entity.Product;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private double totalPrice;

    public Cart(){
        items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity){
        CartItem item = new CartItem(product, quantity);
        items.add(item);
    }

    public void removeItem(Product product){
        List<CartItem> list = items;
        for (CartItem item : list){
            if (item.getProduct().getId().equals(product.getId())){
                items.remove(item);
                break;
            }
        }
    }

    public void updateItem(Product product, int quantity){
        List<CartItem> list = items;
        for (CartItem item : list){
            if (item.getProduct().getId().equals(product.getId())){
                item.setQuantity(quantity);
                item.setAmount(item.getQuantity() * item.getProduct().getPrice());
                break;
            }
        }
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for (CartItem item : items){
            totalPrice += item.getAmount();
        }
        return totalPrice;
    }

    public List<Product> getProducts(){
        List<Product> result = new ArrayList<>();
        for (CartItem item : items){
            result.add(item.getProduct());
        }
        return result;
    }
}
