package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "userlog")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdat")
    private LocalDate createdAt;

    @Column(name = "ipaddress")
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "appuserid")
    private AppUser appUser;


}
