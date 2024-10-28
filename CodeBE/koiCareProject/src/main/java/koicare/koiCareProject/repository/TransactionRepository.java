package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Payment;
import koicare.koiCareProject.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> getAllByPayment(Payment payment);
}
