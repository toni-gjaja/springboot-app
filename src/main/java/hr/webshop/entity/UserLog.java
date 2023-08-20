package hr.webshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "userlog")
public class UserLog {

    public UserLog(Date createdAt, String ipAddress, AppUser appUser){
        this.createdAt = createdAt;
        this.ipAddress = ipAddress;
        this.appUser = appUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdat")
    private Date createdAt;

    @Column(name = "ipaddress")
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "appuserid")
    private AppUser appUser;

}

