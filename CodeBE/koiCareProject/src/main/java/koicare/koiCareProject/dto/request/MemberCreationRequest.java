package koicare.koiCareProject.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberCreationRequest {

    private String memberName;
    private String memberEmail;
    private String memberPhone;


}
