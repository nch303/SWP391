package koicare.koiCareProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid", nullable = false)
    private long accountID;

    private String username;


    private boolean status ;

    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    float balance = 0;


    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Invalid email!")
    private String email;

    @Enumerated(EnumType.STRING)
    Role role;


    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Member member;


    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Shop shop;

    @OneToMany(mappedBy = "customer")
    List<Orders> orders;

    @OneToMany(mappedBy = "from")
    Set<Transactions> transactionsFrom;

    @OneToMany(mappedBy = "to")
    Set<Transactions> transactionsTo;

    @OneToMany (mappedBy = "account")
    Set<Apackage> apackages;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
