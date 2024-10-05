package koicare.koiCareProject.dto.request;

import koicare.koiCareProject.entity.Account;
import lombok.Data;

@Data
public class EmailDetail {
    Account account;
    String subject;
    String link;
}
