package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    private long productTypeID;
    private String productTypeName;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDetail> postDetails;
}
