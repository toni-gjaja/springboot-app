package hr.webshop.repository;

import hr.webshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByDeletedAtIsNull();
}
