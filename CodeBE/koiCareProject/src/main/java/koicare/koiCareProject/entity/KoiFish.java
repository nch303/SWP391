package koicare.koiCareProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class KoiFish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long koiFishID;


    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Koi name must not contain special characters!")
    @Size(max = 25, message = "Name must be less than 25 characters!")
    private String koiName;


    private Date birthday;

    private long age;


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
