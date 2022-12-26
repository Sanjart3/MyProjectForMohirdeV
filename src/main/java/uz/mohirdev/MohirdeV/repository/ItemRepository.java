package uz.mohirdev.MohirdeV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
