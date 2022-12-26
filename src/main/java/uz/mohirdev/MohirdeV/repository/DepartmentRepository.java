package uz.mohirdev.MohirdeV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
