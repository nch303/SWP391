package koicare.koiCareProject.dto.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import koicare.koiCareProject.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {


    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    private String name;

    private String phone;

    @Email(message = "Invalid email!")
    private String email;

    @Enumerated(EnumType.STRING)
    Role role;



}
