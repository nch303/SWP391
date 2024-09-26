package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pondid")
    private long pondID;

    private String pondName;
    private String pondImage;
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

    @OneToMany(mappedBy = "pond", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoiFish> koiFishes;

    @OneToMany(mappedBy = "pond", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterReport> pondReports;


}
