package koicare.koiCareProject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberCreationRequest {

    @NotBlank(message = "Name can not be blank!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must not contain special characters!")
    @Size(max = 20, message = "Name must be less than 20 characters!")
    private String memberName;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Invalid email!")
    private String memberEmail;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone!")
    private String memberPhone;


}
