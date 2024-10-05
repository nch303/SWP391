package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class KoiFish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long koiFishID;

    private String koiName;
    private Date birthday;
    private String koiSex;
    private String image;

    @ManyToOne
    @JoinColumn(name = "pondid")
    private Pond pond;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "koivarietyid")
    private KoiVariety koiVariety;

    @OneToMany(mappedBy = "koiFish")
    private List<KoiReport> koiReports;


}
