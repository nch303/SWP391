package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Orders;
import koicare.koiCareProject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment getByOrders(Orders orders);

}
