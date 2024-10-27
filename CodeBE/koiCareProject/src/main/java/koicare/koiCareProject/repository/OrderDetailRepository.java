package koicare.koiCareProject.repository;

import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.entity.Apackage;
import koicare.koiCareProject.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    OrderDetail getByOrderId(UUID orderID);

    List<OrderDetail> getAllByApackage(Apackage apackage);


}
