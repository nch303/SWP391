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
    private int area;
    private int depth;
    private int volume;
    private int drainCount;
    private int skimmerCount;
    private int amountFish;
    private int pumpingCapacity;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;


}
