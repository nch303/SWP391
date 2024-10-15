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
    private long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Account to;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private TransactionsEnum status;

    private String description;
}
