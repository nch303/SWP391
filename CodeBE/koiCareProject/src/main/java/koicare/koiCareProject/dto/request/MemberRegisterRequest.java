package koicare.koiCareProject.dto.request;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRegisterRequest {

    String username;

    @Size(min = 6, message = "Password must be at least 6 characters!")
    String password;
    String role;
}
