package uz.mohirdev.MohirdeV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.MohirdeV.Entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
