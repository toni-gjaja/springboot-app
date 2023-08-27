package hr.webshop.service;

import hr.webshop.model.AdminProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AppUserService appUserService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ReceiptService receiptService;
    private final UserLogService userLogService;

    @Autowired
    public AdminService(AppUserService appUserService, CategoryService categoryService, ProductService productService, ReceiptService receiptService, UserLogService userLogService) {
        this.appUserService = appUserService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.receiptService = receiptService;
        this.userLogService = userLogService;
    }

    public AppUserService getAppUserService() {
        return appUserService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public UserLogService getUserLogService() {
        return userLogService;
    }

    public AdminProfileModel getAllData(){

        return new AdminProfileModel(
                appUserService.getAllUsers(),
                categoryService.getAllCategories(),
                productService.getAllProducts(),
                receiptService.getAllReceipts(),
                userLogService.getAllLogs()
                );
    }

}
