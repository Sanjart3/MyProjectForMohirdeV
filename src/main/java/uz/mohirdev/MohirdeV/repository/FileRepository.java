package uz.mohirdev.MohirdeV.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.FileStorage;

@Repository
public interface FileRepository extends JpaRepository<FileStorage, Long> {

    FileStorage findByHashId(String hashId);
}
