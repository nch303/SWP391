package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class PostDetailRequest {

    private String productName;
    private double productPrice;
    private String image;
    private String description;
    private String link;
    private Date postDate;
    private boolean postStatus;
    private Long shopID;
    private Long producTypeID;
    private Long priceID;
    private Long paymentID;

}
