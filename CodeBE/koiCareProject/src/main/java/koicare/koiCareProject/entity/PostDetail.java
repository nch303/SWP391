package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class PostDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private long postID;
    private String productName;
    private double productPrice;
    private String image;
    private String description;
    private String link;
    private Date postDate;
    private boolean postStatus;

    
    @ManyToOne
    @JoinColumn(name = "shopid")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "priceid")
    private PostPrice postPrice;

    @ManyToOne
    @JoinColumn(name = "paymentid")
    private Payment payment;
}
