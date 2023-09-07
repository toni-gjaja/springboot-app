package hr.webshop.repository;

import hr.webshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByDeletedAtIsNull();

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock = :newStock WHERE p.id = :productId")
    void updateStock(@Param("productId") Long productId, @Param("newStock") int newStock);
}
