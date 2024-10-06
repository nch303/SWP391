package koicare.koiCareProject.dto.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import koicare.koiCareProject.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {


    @NotBlank(message = "Username can not be blank!")
    private String username;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @NotBlank(message = "Name can not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must not contain special characters!")
    @Size(max = 20, message = "Name must be less than 20 characters!")
    private String name;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone!")
    private String phone;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Invalid email!")
    private String email;

    @Enumerated(EnumType.STRING)
    Role role;



}
