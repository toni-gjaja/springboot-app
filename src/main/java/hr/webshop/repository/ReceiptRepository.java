package hr.webshop.repository;

import hr.webshop.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findAllByAppUser_Id(Long id);
}
