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

    @NotBlank(message = "Name can not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Koi name must not contain special characters!")
    @Size(max = 10, message = "Name must be less than 10 characters!")
    private String koiName;

    @NotBlank(message = "Birthday can not be blank!")
    private Date birthday;

    @NotBlank(message = "This fiel can not be blank!")
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
