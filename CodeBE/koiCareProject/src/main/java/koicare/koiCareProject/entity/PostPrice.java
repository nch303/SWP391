package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class PostPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceid")
    private Long priceID;
    private int duration;
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "postPrice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDetail> postDetails;
}
