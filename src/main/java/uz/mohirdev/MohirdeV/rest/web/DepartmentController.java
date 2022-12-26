package uz.mohirdev.MohirdeV.rest.web;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.Department;
import uz.mohirdev.MohirdeV.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/adder")
    public ResponseEntity save(@RequestBody Department department){
        Department result = departmentService.save(department);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/departments")
    public ResponseEntity update(@RequestBody Department department){
        Department result = departmentService.save(department);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/departments")
    public ResponseEntity delete(@RequestParam Long id){
        departmentService.delete(id);
        return ResponseEntity.ok("The data has been deleted!");
    }

    @GetMapping("departments/{id}")
    public ResponseEntity getData(@PathVariable Long id){
        Department result = departmentService.getData(id);
        return ResponseEntity.ok(result);
    }
}
