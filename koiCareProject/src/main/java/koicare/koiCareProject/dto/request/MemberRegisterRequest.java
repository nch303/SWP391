package koicare.koiCareProject.dto.request;


import lombok.Data;

@Data
public class MemberRegisterRequest {

    String username;
    String password;
    String role;
}
