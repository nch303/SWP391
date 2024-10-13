package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.request.OrderRequest;
import koicare.koiCareProject.entity.Account;
import koicare.koiCareProject.entity.Orders;
import koicare.koiCareProject.repository.OrderRepository;
import koicare.koiCareProject.service.AuthenticationService;
import koicare.koiCareProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/order")
@SecurityRequirement(name = "api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequest orderRequest) throws Exception {
        String vnPayURL = orderService.createUrl(orderRequest);
        return ResponseEntity.ok(vnPayURL);
    }


    @GetMapping
    public ResponseEntity get(){
        Account account = authenticationService.getCurrentAccount();
        List<Orders> orders = orderRepository.findOrderssByCustomer(account);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("transactions")
    public ResponseEntity create (@RequestParam UUID orderID){
        orderService.createTransactions(orderID);
        return ResponseEntity.ok("Success");
    }
}
