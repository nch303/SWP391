package koicare.koiCareProject.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class AccountResponse {

    private long accountID;

    private String username;
    private String password;
    private String name;
    private String phone;
    private String role;
    private String email;
    private int premiumStatus;
    private Date expiredDate;
    private String token;

}
