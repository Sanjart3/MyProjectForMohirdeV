package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.Account;
import uz.mohirdev.MohirdeV.Entity.Department;
import uz.mohirdev.MohirdeV.Entity.Employee;
import uz.mohirdev.MohirdeV.repository.DepartmentRepository;
import uz.mohirdev.MohirdeV.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final AccountService accountService;

    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, AccountService accountService, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.accountService = accountService;
        this.departmentService = departmentService;
    }

    public Employee save(Employee employee){
        if (employee.getDepartment().getId()==null){
            Department department = departmentService.save(employee.getDepartment());
            if (department!=null){
                employee.getDepartment().setId(department.getId());
            }
        }

        if(employee.getAccount().getId()==null){
            Account account = accountService.save(employee.getAccount());
            if (account==null){
                employee.getAccount().setId(account.getId());
            }
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee){
        Employee result = employeeRepository.save(employee);
        return result;
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee findById(Long id){
        Optional<Employee> optional=employeeRepository.findById(id);
        if (optional.isPresent()) return optional.get();
        else return null;
    }

    public List<Employee> findAll1(String name){
        List<Employee> employees = employeeRepository.findAll(name);
        return employees;
    }

    public List<Employee> getAllByMathedQueryLike(String name){
        return employeeRepository.findAllByNameLike(name);
    }

    public List<Employee> findByMathedQuery(String name){
//        return employeeRepository.findAllByNameStartingWith(name);

        return employeeRepository.findAllByNameStartingWithOrderByIdDesc(name);
    }

    public List<Employee> getAllLikeName(String name){
        return employeeRepository.getAllLikeName(name);
    }

    public List<Employee> getAllLikeNative(String name) {

        return employeeRepository.getAllLikeNative(name);
    }

}
