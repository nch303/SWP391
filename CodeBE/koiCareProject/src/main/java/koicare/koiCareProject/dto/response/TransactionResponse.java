package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionResponse {

    UUID orderID;
    Date date;
    String apackage;
    float price;
    int duration;
//    String payment_method;

}
