package hr.webshop.model;

import hr.webshop.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdminProfileModel {

    List<AppUser> appUsers;
    List<Category> categories;
    List<Product> products;
    List<Receipt> receipts;
    List<UserLog> userLogs;


}
