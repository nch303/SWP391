package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import koicare.koiCareProject.enums.TransactionsEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    Account from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    Account to;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;

    TransactionsEnum status;

    String description;
}
