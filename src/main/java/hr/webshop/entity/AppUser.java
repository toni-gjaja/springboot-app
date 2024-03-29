package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "appuser")
public class AppUser {

    public AppUser(){
        createdAt = LocalDate.now();
        role = "ROLE_USER";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "createdat")
    private LocalDate createdAt;
    @Column(name = "role")
    private String role;

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
