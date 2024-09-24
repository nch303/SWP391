package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.datetime.standard.DateTimeContextHolder;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid")
    private long memberID;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private int premiumStatus;
    private Date expiredDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    @JsonManagedReference
    private Account account;

    @OneToMany(mappedBy = "Member", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pond> ponds;


}
