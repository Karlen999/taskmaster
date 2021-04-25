package im.itspace.taskmaster.service;

import im.itspace.taskmaster.exception.ResourceNotFoundException;
import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project create(Project project){
       return projectRepository.save(project);
    }

    public List<Project> allProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> allUserProjects(User user) {

        return projectRepository.findByUser(user);
    }

    public Project findById(int id) {

        return projectRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) throws ResourceNotFoundException {
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()){
            projectRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Project does not exist.");
        }
    }

    public Project update(Project project, int id){
        Project projectFromDb = projectRepository.findById(id).orElseThrow( () ->new ResourceNotFoundException("User does not exists"));
        projectFromDb.setName(project.getName());
        return projectRepository.save(projectFromDb);
    }
}
