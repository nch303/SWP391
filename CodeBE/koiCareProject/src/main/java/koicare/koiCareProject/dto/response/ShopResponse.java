package koicare.koiCareProject.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ShopResponse {
    private long shopID;
    private String name;
    private String email;
    private String phone;
    private int numberOfPosts;
    private Date expiredDate;
}
