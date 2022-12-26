package uz.mohirdev.MohirdeV.rest.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.Project;
import uz.mohirdev.MohirdeV.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity save(@RequestBody Project project){
        Project result = projectService.save(project);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/projects")
    public ResponseEntity update(@RequestBody Project project){
        if (project.getId()==null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok(projectService.save(project));
    }

    @DeleteMapping("/projects")
    public ResponseEntity delete(@PathVariable Long id){
        projectService.delete(id);
        return ResponseEntity.ok("Data has been deleted");
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity get(@PathVariable Long id){
        Project result = projectService.get(id);
        return ResponseEntity.ok(result);
    }
}
