package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class MemberPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_package_id")
    private long packageID;
    private int duration;
    private double price;

    @OneToMany(mappedBy = "memberPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberOrder> memberOrders;



}
