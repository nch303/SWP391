package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class MemberOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberOrderID;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "member_package_id")
    private MemberPackage memberPackage;

    @OneToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "paymentid")
    private Payment payment;
}
