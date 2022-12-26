package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.Account;
import uz.mohirdev.MohirdeV.repository.AccountRepository;

@Service
public class AccountService{

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account save(Account account){
        Account account1 = accountRepository.save(account);
        return account1;
    }
}
