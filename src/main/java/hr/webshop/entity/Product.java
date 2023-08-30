package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @Column(name = "deletedat")
    private LocalDate deletedAt;

    public Product(String name, int stock, double price, Category category) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.category = category;
    }
}
