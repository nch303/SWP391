package koicare.koiCareProject.dto.response;

import lombok.Data;

@Data
public class PostPriceResponse {
    private long priceID;
    private int duration;
    private double price;
}
