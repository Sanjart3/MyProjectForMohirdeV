package uz.mohirdev.MohirdeV.rest.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.Department;
import uz.mohirdev.MohirdeV.Entity.Employee;
import uz.mohirdev.MohirdeV.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){

        if(employee.getId()==0) return ResponseEntity.badRequest().build();

        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        employeeService.delete(id);
        return ResponseEntity.ok("The line has been deleted");
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> read(@PathVariable Long id){
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees")
    public ResponseEntity getList(@RequestParam String name){
        List<Employee> result = employeeService.findAll1(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/search")
    public ResponseEntity searchByQueryLike(@RequestParam String name){
//        List<Employee> result = employeeService.getAllByMathedQueryLike(name);

//        List<Employee> result = employeeService.findByMathedQuery(name);

        List<Employee> result = employeeService.getAllLikeName(name);

//        List<Employee> result = employeeService.getAllLikeNative(name);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/employees")
    public ResponseEntity littleUpdate(@RequestBody Employee employee){
        Employee result = employeeService.update(employee);
        return ResponseEntity.ok(result);
    }

}
