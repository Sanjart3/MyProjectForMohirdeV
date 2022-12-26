package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.Project;
import uz.mohirdev.MohirdeV.repository.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project){
        return projectRepository.save(project);
    }

    public Project get(Long id){
        Optional<Project> result = projectRepository.findById(id);
        if (result.isPresent()) return result.get();
        else return null;
    }

    public void delete(Long id){
        projectRepository.deleteById(id);
    }
}
