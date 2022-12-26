package uz.mohirdev.MohirdeV.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;
import uz.mohirdev.MohirdeV.Entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.name=:name")
    List<Employee> findAllBy(@Param("name") String name);

    @Query(value = "SELECT * FROM employee e where e.last_name=:name", nativeQuery=true)
    List<Employee> findAll(@Param("name") String name);

    List<Employee> findAllByNameStartingWith(String name);

    List<Employee> findAllByNameLike(String name);

    @Query("select e from Employee e where upper(e.name) like upper(concat(:name,'%')) order by e.email asc")
    List<Employee> getAllLikeName(@Param("name") String name);

    @Query(value = "select * from employee e where e.name like :name% and id>1 order by id asc", nativeQuery = true)
    List<Employee> getAllLikeNative(String name);

    List<Employee> findAllByNameStartingWithOrderByIdDesc(String name);
}
