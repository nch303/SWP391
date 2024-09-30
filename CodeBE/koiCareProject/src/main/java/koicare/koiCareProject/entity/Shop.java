package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopid")
    private long shopID;
    private String name;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Account account;
}