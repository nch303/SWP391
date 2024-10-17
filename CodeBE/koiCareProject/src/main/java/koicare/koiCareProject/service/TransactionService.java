package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Orders;
import koicare.koiCareProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ApackageRepository apackageRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<TransactionResponse> viewAllTransaction(){
        List<TransactionResponse> responses = new ArrayList<>();

        List<Orders> orders = orderRepository.findAll();
        for(Orders orders1: orders){
            TransactionResponse response = new TransactionResponse();
            response.setOrderID(orders1.getId());
            response.setDate(orders1.getDate());
            response.setApackage(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getName());
            response.setDuration(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getDuration());
            response.setPrice(orders1.getTotal());
//          response.setPayment_method(paymentRepository.getByOrders(orders1).getPayment_method().name());

            responses.add(response);
        }
        return responses;
    }

    public List<TransactionResponse> viewTransactionsByAccount(){
        List<TransactionResponse> responses = new ArrayList<>();

        Account customer =   authenticationService.getCurrentAccount();
        List<Orders> orders = orderRepository.findOrderssByCustomer(customer);
        for(Orders orders1: orders){
            TransactionResponse response = new TransactionResponse();
            response.setOrderID(orders1.getId());
            response.setDate(orders1.getDate());
            response.setApackage(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getName());
            response.setDuration(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getDuration());
            response.setPrice(orders1.getTotal());
//          response.setPayment_method(paymentRepository.getByOrders(orders1).getPayment_method().name());

            responses.add(response);
        }
        return responses;
    }


}
