package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdat")
    LocalDate createdAt;

    @Column(name = "amount")
    Double amount;

    @Column(name = "payment")
    String payment;

    @ManyToOne
    @JoinColumn(name = "appuserid")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "productreceipt",
            joinColumns = @JoinColumn(name = "receiptid"),
            inverseJoinColumns = @JoinColumn(name = "productid")
    )
    private List<Product> products;

}
