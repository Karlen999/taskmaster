package im.itspace.taskmaster.endpoint;

import im.itspace.taskmaster.dto.UserDto;
import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.model.UserType;
import im.itspace.taskmaster.repository.ProjectRepository;
import im.itspace.taskmaster.security.CurrentUser;
import im.itspace.taskmaster.security.SecurityService;
import im.itspace.taskmaster.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping("/create")
    public void createProject(@RequestBody Project project) {
        projectService.create(project);
    }


    @PutMapping("/update/{id}")
    public void updateProject(@PathVariable("id") int id, @RequestBody Project project) {
        projectService.update(project, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable("id") int id) {
        projectService.deleteById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> allProjects() {

            return ResponseEntity.ok(projectService.allProjects());

        }

    @GetMapping("/{id}")
    public ResponseEntity ProjectById(@PathVariable("id") int id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @GetMapping("/users-projects")
    public ResponseEntity<?> allUserProjects(User user) {
        if (user.getUserType() == UserType.EMPLOYER) {
            return ResponseEntity.ok(projectService.allUserProjects(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

