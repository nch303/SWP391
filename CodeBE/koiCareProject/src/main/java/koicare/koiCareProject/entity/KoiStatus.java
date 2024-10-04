package koicare.koiCareProject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class KoiStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long koiStatusID;

    private String statusContent;

    @OneToMany(mappedBy = "koiStatus")
    private List<KoiReport> koiReports;

}
