package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<OrderDetailRequest> detail;
}
