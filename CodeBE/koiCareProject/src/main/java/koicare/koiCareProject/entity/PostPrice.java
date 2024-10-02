package koicare.koiCareProject.entity;

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

    @OneToMany(mappedBy = "postPrice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDetail> postDetails;
}