package koicare.koiCareProject.dto.response;


import lombok.Data;

@Data
public class AccountResponse {

    private int accountID;

    private String name;
    private String password;
    private String role;
}
