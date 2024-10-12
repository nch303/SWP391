package koicare.koiCareProject.service;

import koicare.koiCareProject.dto.request.OrderDetailRequest;
import koicare.koiCareProject.dto.request.OrderRequest;
import koicare.koiCareProject.dto.response.MemberResponse;
import koicare.koiCareProject.dto.response.PaymentResponse;
import koicare.koiCareProject.entity.*;
import koicare.koiCareProject.enums.PaymentEnums;
import koicare.koiCareProject.enums.TransactionsEnum;
import koicare.koiCareProject.exception.AppException;
import koicare.koiCareProject.exception.ErrorCode;
import koicare.koiCareProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ApackageRepository apackageRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    MemberRepository memberRepository;


    public Orders create(OrderRequest orderRequest){

        Account customer = authenticationService.getCurrentAccount();
        Orders order = new Orders();
        List<OrderDetail> orderDetails = new ArrayList<>();
        float total = 0;

        order.setCustomer(customer);
        order.setDate(new Date()); //current date

        for(OrderDetailRequest orderDetailRequest: orderRequest.getDetail()){
            Apackage apackage = apackageRepository.findApackageById(orderDetailRequest.getPackageID());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(apackage.getPrice());
            orderDetail.setOrder(order);
            orderDetail.setApackage(apackage);

            orderDetails.add(orderDetail);

            total += apackage.getPrice();
        }

        order.setOrderDetails(orderDetails);
        order.setTotal(total);

        return orderRepository.save(order);
    }

    public String createUrl(OrderRequest orderRequest) throws  Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

        //code  cua minh
        //tao order
        Orders orders = create(orderRequest);
        //
        float money = orders.getTotal() * 100;
        String amount = String.valueOf((int) money);


        String tmnCode = "QRAI4K9E";
        String secretKey = "49WRMPUBCXWRNU4NVHTMUML5G9DNBQCW";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        //trang thanh toan thanh cong
        String returnUrl = "https://blearning.vn/guide/swp/docker-local?orderID=" + orders.getId();
        String currCode = "VND";

        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", orders.getId().toString());
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + orders.getId());
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount",amount);

        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "128.199.178.23");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return urlBuilder.toString();
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public void createTransactions(UUID uuid){
        //tim cai order
        Orders orders = orderRepository.findById(uuid)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_IS_NOT_EXISTED));

        //tao payment
        Payment payment = new Payment();
        payment.setOrders(orders);
        payment.setCreateAt(new Date());
        payment.setPayment_method(PaymentEnums.BANKING);

        Set<Transactions> setTransactions = new HashSet<>();

        //tao Transaction
        Transactions transactions1 = new Transactions();

        //VNPAY TO CUSTOMER
        Account customer = authenticationService.getCurrentAccount();
        transactions1.setFrom(null);
        transactions1.setTo(customer);
        transactions1.setPayment(payment);
        transactions1.setStatus(TransactionsEnum.SUCCESS);
        transactions1.setDescription("NAP TIEN VNPAY TO CUSTOMER");
        setTransactions.add(transactions1);

        //tao Transaction
        Transactions transactions2 = new Transactions();

        //CUSTOMER TO ADMIN
        Account admin = accountRepository.findAccountByRole(Role.ADMIN);
        transactions2.setFrom(customer);
        transactions2.setTo(admin);
        transactions2.setPayment(payment);
        transactions2.setStatus(TransactionsEnum.SUCCESS);
        transactions2.setDescription("CUSTOMER TO ADMIN");
        float newBalance = admin.getBalance() + orders.getTotal();
        admin.setBalance(newBalance);
        setTransactions.add(transactions2);

//        //tao Transaction
//        Transactions transactions3 = new Transactions();
//
//        //ADMIN TO CUSTOMER
//        transactions3.setPayment(payment);
//        transactions3.setStatus(TransactionsEnum.SUCCESS);
//        transactions3.setDescription("ADMIN TO CUSTOMER");
//        transactions3.setFrom(admin);
//        Account owner = orders.getOrderDetails().get(0).getKoi().getAccount();
//        transactions3.setTo(owner);
//        float newShopBalance = owner.getBalance() + orders.getTotal() * 0.9f;
//        owner.setBalance(newShopBalance);
//        setTransactions.add(transactions3);



        payment.setTransactions(setTransactions);

        accountRepository.save(admin);
        paymentRepository.save(payment);


        //set gia tri numberOfpost va ExpiredDate cho shop hoac member
        if(customer.getRole().toString().contains("SHOP")){
            Shop shop = shopRepository.getShopByAccount(customer);

            //cap nhat numberOfPost
            int newNumberOfPost = orderDetailRepository.getByOrderId(orders.getId()).getApackage().getNumberOfPosts();
            shop.setNumberOfPosts(shop.getNumberOfPosts() + newNumberOfPost);

            //cap nhat expiredDate
            int numberOfMonths = orderDetailRepository.getByOrderId(orders.getId()).getApackage().getDuration();
            Date newExpiredDate = orders.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newExpiredDate);
            calendar.add(Calendar.MONTH, numberOfMonths);
            shop.setExpiredDate(calendar.getTime());
            shop.setShopID(shop.getShopID());

            shopRepository.save(shop);
        }
        if(customer.getRole().toString().contains("MEMBER")){
            Member member = memberRepository.getMemberByAccount(customer);

            //cap nhat expiredDate
            int numberOfMonths = orderDetailRepository.getByOrderId(orders.getId()).getApackage().getDuration();
            Date newExpiredDate = orders.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newExpiredDate);
            calendar.add(Calendar.MONTH, numberOfMonths);
            member.setExpiredDate(calendar.getTime());
            member.setMemberID(member.getMemberID());

            //cap nhat premiumStatus
            member.setPremiumStatus(1);

            memberRepository.save(member);
        }
    }
}
