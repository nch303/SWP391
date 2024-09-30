package koicare.koiCareProject.dto.response;


import lombok.Data;

@Data
public class AccountResponse {

    private long accountID;

    private String username;
    private String password;
    private String role;
    private String email;
    private String token;
}
