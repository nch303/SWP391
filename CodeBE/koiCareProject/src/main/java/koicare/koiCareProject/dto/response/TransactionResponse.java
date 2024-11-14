package koicare.koiCareProject.dto.response;

import jakarta.persistence.OrderBy;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionResponse {

    private UUID orderID;
    private String orderCode;
    private Date date;
    private String apackage;
    private float price;
    private int duration;
    private String status;
    private String customer;
//    String payment_method;

}
