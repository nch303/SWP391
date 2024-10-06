package koicare.koiCareProject.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;
}