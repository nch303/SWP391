package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class KoiStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koistandid")
    private long koiStandID;

    private long period;
    private double lowLengthMale;
    private double medLengthMale;
    private double hiLengthMale;
    private double lowLengthFemale;
    private double medLengthFemale;
    private double hiLengthFemale;
    private double lowWeightMale;
    private double medWeightMale;
    private double hiWeightMale;
    private double lowWeightFemale;
    private double medWeightFemale;
    private double hiWeightFemale;

    @ManyToOne
    @JoinColumn(name = "koivarietyid")
    private KoiVariety koiVariety;
}
