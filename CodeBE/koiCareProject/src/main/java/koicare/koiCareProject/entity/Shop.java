package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopid")
    private long shopID;
    private String name;
    private String email;
    private String phone;
    private int numberOfPosts;
    private Date expiredDate;




    @OneToOne
    @JoinColumn(name = "accountid")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDetail> postDetails;



}