package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name can not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must not contain special characters!")
    @Size(max = 20, message = "Name must be less than 20 characters!")
    private String name;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Invalid email!")
    private String email;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone!")
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
