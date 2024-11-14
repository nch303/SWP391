package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.response.AccountResponse;
import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.enums.TransactionsEnum;
import koicare.koiCareProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionResponse> viewAllTransaction() {
        List<TransactionResponse> responses = new ArrayList<>();

        List<Orders> orders = orderRepository.findAll();
        for (Orders orders1 : orders) {
            TransactionResponse response = new TransactionResponse();
            response.setOrderID(orders1.getId());
            response.setOrderCode(orders1.getOrderCode());
            response.setDate(orders1.getDate());
            response.setApackage(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getName());
            response.setDuration(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getDuration());
            response.setPrice(orders1.getTotal());

            //set name customer cho giao dich
            Account customer = accountRepository.findAccountByAccountID(orders1.getCustomer().getAccountID());
            if (customer.getRole() == Role.MEMBER) {
                response.setCustomer(customer.getMember().getName());
            } else response.setCustomer(customer.getShop().getName());


            //set status of transaction
            Payment payment = paymentRepository.getByOrders(orders1);
            List<Transactions> transactions = transactionRepository.getAllByPayment(payment);
            if (transactions.get(1).getStatus().equals(TransactionsEnum.SUCCESS))
                response.setStatus("SUCCESS");
            else response.setStatus("FAIL");

            responses.add(response);
        }
        return responses;
    }

    public List<TransactionResponse> viewTransactionsByAccount() {
        List<TransactionResponse> responses = new ArrayList<>();

        Account customer = authenticationService.getCurrentAccount();
        List<Orders> orders = orderRepository.findOrderssByCustomer(customer);
        for (Orders orders1 : orders) {
            TransactionResponse response = new TransactionResponse();
            response.setOrderID(orders1.getId());
            response.setOrderCode(orders1.getOrderCode());
            response.setDate(orders1.getDate());
            response.setApackage(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getName());
            response.setDuration(orderDetailRepository.getByOrderId(orders1.getId()).getApackage().getDuration());
            response.setPrice(orders1.getTotal());

            //set status of transaction
            Payment payment = paymentRepository.getByOrders(orders1);
            List<Transactions> transactions = transactionRepository.getAllByPayment(payment);
            if (transactions.get(1).getStatus().equals(TransactionsEnum.SUCCESS))
                response.setStatus("SUCCESS");
            else response.setStatus("FAIL");
            responses.add(response);
        }
        return responses;
    }

//    public TransactionResponse viewTransactionsByID(UUID id) {
//
//        Account customer = authenticationService.getCurrentAccount();
//        Orders order = orderRepository.findOrdersById(id);
//
//        TransactionResponse response = new TransactionResponse();
//        response.setOrderID(order.getId());
//        response.setOrderCode(order.getOrderCode());
//        response.setDate(order.getDate());
//        response.setApackage(orderDetailRepository.getByOrderId(order.getId()).getApackage().getName());
//        response.setDuration(orderDetailRepository.getByOrderId(order.getId()).getApackage().getDuration());
//        response.setPrice(order.getTotal());
//
//        //set status of transaction
//        Payment payment = paymentRepository.getByOrders(order);
//        List<Transactions> transactions = transactionRepository.getAllByPayment(payment);
//        if (transactions.get(1).getStatus().equals(TransactionsEnum.SUCCESS))
//            response.setStatus("SUCCESS");
//        else response.setStatus("FAIL");
//
//        return response;
//    }


}
