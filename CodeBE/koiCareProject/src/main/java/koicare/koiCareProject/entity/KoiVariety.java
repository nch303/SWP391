package koicare.koiCareProject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class KoiVariety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koivarietyid")
    private long koiVarietyID;

    private String varietyName;

    @OneToMany(mappedBy = "koiVariety", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoiStandard> koiStandards;

    @OneToMany(mappedBy = "koiVariety", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoiFish> koiFishes;
}
