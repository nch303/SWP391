package koicare.koiCareProject.entity;


import jakarta.persistence.*;
import koicare.koiCareProject.enums.PaymentEnums;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Date createAt;

    @Enumerated(EnumType.STRING)
    PaymentEnums payment_method;

    @OneToOne
    @JoinColumn(name = "order_id")
    Orders orders;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    Set<Transactions> transactions;
}
