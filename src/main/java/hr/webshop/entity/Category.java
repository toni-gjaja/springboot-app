package hr.webshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
