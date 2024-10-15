package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {

    List<Orders> findOrderssByCustomer(Account customer);
}
