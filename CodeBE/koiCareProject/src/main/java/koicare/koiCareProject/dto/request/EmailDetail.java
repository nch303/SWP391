package koicare.koiCareProject.dto.request;

import koicare.koiCareProject.entity.Account;
import lombok.Data;

@Data
public class EmailDetail {
    private Account account;
    private String subject;
    private String link;
}
