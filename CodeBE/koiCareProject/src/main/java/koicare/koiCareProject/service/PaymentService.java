package koicare.koiCareProject.service;

import koicare.koiCareProject.entity.Payment;
import koicare.koiCareProject.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
}
