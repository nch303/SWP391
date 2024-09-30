package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid")
    private long memberID;
    private String name;
    private String email;
    private String phone;
    private int premiumStatus;
    private Date expiredDate;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pond> ponds;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoiFish> koiFishes;

    @OneToOne
    @JoinColumn(name = "accountid")
    private Account account;

}
