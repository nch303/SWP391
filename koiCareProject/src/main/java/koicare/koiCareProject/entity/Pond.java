package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Pond")
public class Pond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pondID;
    private String pondName;
    private double area;
    private double depth;
    private long drainCount;
    private long skimmerCount;
    private long amountFish;
    private long pumpingCapacity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberid", nullable = false, referencedColumnName = "memberid")
    @JsonManagedReference
    private Member member;
}
