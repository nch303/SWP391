package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class PostDetailResponse {

    private Long postDetailId;
    private String productName;
    private double productPrice;
    private String image;
    private String description;
    private String link;
    private Date postDate;
    private boolean postStatus;
    private Long shopID;
    private Date expiredDate;
    private Long producTypeID;

}
