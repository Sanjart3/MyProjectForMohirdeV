package uz.mohirdev.MohirdeV.rest.web;


import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.Account;
import uz.mohirdev.MohirdeV.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity save(@RequestBody Account account){
        return ResponseEntity.ok(accountService.save(account));
    }

    @PutMapping("/accounts")
    public ResponseEntity update(@RequestBody Account account){
        if (account.getId()==null) return null;
        Account result = accountService.save(account);
        return ResponseEntity.ok(result);
    }
}
