package uz.mohirdev.MohirdeV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
