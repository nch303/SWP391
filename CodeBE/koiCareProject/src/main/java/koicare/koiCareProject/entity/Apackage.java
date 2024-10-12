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
    UUID id;

    String name;
    Role role;
    int duration;
    float price;
    String description;
    int numberOfPosts;

    @OneToMany(mappedBy = "apackage")
    @JsonIgnore
    List<OrderDetail> orderDetails;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    Account account;

}
