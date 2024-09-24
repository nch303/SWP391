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
    private double lengthMale;
    private double lengthFemale;
    private double weightMale;
    private double weightFemale;
    private double foodMale;
    private double foodFemale;

    @OneToMany(mappedBy = "koiStandard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoiVariety> koiVarieties;
}
