package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "appuser")
public class AppUser {

    public AppUser(){
        createdAt = new Date();
        isAdmin = false;
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
    private Date createdAt;
    @Column(name = "isadmin")
    private boolean isAdmin;

}
