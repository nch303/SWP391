package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Apackage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Role role;
    private int duration;
    private float price;
    private String description;
    private int numberOfPosts;

    @OneToMany(mappedBy = "apackage")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

}
