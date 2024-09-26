package koicare.koiCareProject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreationRequest {

    private String memberName;
    private String memberEmail;
    private String memberPhone;


}
