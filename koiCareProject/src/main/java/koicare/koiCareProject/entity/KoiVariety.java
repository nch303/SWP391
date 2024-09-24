package koicare.koiCareProject.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class KoiVariety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koivarietyid")
    private long koiVarietyID;

    private String varietyName;

    @ManyToOne
    @JoinColumn(name = "koistandid")
    private KoiStandard koiStandard;
}
