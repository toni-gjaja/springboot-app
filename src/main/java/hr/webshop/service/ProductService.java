package hr.webshop.service;

import hr.webshop.entity.Product;
import hr.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;
    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Optional<Product> getProductById(Long id){ return repo.findById(id); }

    public void removeProduct(Long id){ repo.deleteById(id); }

    public void saveProduct(Product product){ repo.save(product); }
}
