package koicare.koiCareProject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    int quantity;
    private float price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "apackage_id")
    private Apackage apackage;
}
