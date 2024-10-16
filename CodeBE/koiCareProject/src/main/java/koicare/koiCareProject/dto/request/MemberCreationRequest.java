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


    private String memberName;

    private String memberEmail;

    private String memberPhone;



}
