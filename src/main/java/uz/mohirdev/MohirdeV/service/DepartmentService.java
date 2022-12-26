package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.Department;
import uz.mohirdev.MohirdeV.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public void delete(Long id){
        departmentRepository.deleteById(id);
    }

    public Department getData(Long id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) return department.get();
        else return null;
    }
}
