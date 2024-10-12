package koicare.koiCareProject.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    List<OrderDetailRequest> detail;
}
