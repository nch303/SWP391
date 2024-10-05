package koicare.koiCareProject.repository;

import koicare.koiCareProject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByPaymentID(Long paymentID);
}
