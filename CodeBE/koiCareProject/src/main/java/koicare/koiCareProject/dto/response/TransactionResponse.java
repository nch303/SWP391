package koicare.koiCareProject.dto.response;

import jakarta.persistence.OrderBy;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionResponse {

    UUID orderID;
    String orderCode;
    Date date;
    String apackage;
    float price;
    int duration;
    String status;
//    String payment_method;

}
