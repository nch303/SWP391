package koicare.koiCareProject.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class MemberResponse {

    private long memberID;
    private String name;
    private String email;
    private String phone;
    private Date expiredDate;

}
