package koicare.koiCareProject.APIcontroller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import koicare.koiCareProject.dto.response.TransactionResponse;
import koicare.koiCareProject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/transaction")
@SecurityRequirement(name = "api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("viewAll")
    public ResponseEntity viewAllTransactions(){
        List<TransactionResponse> responses = transactionService.viewAllTransaction();
        Collections.sort(responses, Comparator.comparing(TransactionResponse::getDate).reversed());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("view/currentAccount")
    public ResponseEntity viewTransactionsByAccount(){
        List<TransactionResponse> responses = transactionService.viewTransactionsByAccount();
        Collections.sort(responses, Comparator.comparing(TransactionResponse::getDate).reversed());
        return ResponseEntity.ok(responses);
    }

//    @GetMapping("viewByID/{ID}")
//    public ResponseEntity viewTransactionsByAccount(@PathVariable UUID id){
//        TransactionResponse response = transactionService.viewTransactionsByID(id);
//        return ResponseEntity.ok(response);
//    }
}
