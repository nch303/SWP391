package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    Date date;

    float total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    Account customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "orders")
    Payment payment;
}
