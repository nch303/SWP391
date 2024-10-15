package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
@SecurityRequirement(name = "api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("viewAll")
    public ResponseEntity viewAllTransactions(){
        List<TransactionResponse> responses = transactionService.viewAllTransaction();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("view/currentAccount)")
    public ResponseEntity viewTransactionsByAccount(){
        List<TransactionResponse> responses = transactionService.viewTransactionsByAccount();
        return ResponseEntity.ok(responses);
    }
}
