package uz.mohirdev.MohirdeV.rest.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.model.Course;
import uz.mohirdev.MohirdeV.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
class StudentResource{

//    @GetMapping("/students")
//    public ResponseEntity gettingData(){
//        return ResponseEntity.ok("Mohirdev's wordl");
//    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students")
    public ResponseEntity update(@RequestBody Student student){
        student.setLastName("test uchun");
        return ResponseEntity.ok(student);
    }


    @PutMapping("/students/{id}")
    public ResponseEntity update_Second(@PathVariable Long id, @RequestBody Student student){
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteData(@PathVariable Long id){
        return ResponseEntity.ok(id+ " idlik ma'lumot o'chirildi");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id){
        Student student = new Student();
        student.setId(id);
        student.setName("Sannn");
        student.setAge(32);
        student.setLastName("Mev");
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getAll(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String lastName,
                                 @RequestParam Integer age){

        Student student = new Student();
        Course courses = new Course(4L, "Kuchamala");
        List<Student> students = new ArrayList<>();

        students.add(new Student(id, name, lastName, age, courses));
        students.add(new Student(14L, "name", "lastName", 45, courses));

        return ResponseEntity.ok(students);
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<Student> patchUpdate(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }



    @PostMapping("/students/list")
    public ResponseEntity createAll(@RequestBody List<Student> students){
        // there should be some business logic

        return ResponseEntity.ok(students);
    }
}