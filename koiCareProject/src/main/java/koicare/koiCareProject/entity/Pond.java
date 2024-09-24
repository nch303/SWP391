package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Pond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pondid")
    private long pondID;

    private String pondName;
    private long area;
    private double depth;
    private long volume;
    private long drainCount;
    private long skimmerCount;
    private long amountFish;
    private long pumpingCapacity;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

}
