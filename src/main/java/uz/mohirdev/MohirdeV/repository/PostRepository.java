package uz.mohirdev.MohirdeV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.PostData;

@Repository
public interface PostRepository extends JpaRepository<PostData, Long> {


}
