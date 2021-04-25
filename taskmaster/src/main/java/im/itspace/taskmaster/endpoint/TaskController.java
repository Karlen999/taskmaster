package im.itspace.taskmaster.endpoint;

import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.Status;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());

    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Task>> getByStatus(@PathVariable("status") Status status){
        return new ResponseEntity<>(taskService.getByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Task>> getTasksByUser(@RequestBody User user){
        return ResponseEntity.ok(taskService.getTasksByUser(user));
    }

    @GetMapping("/project")
    public ResponseEntity<Optional<Task>> getTaskByProject(@RequestBody Project project){
        return ResponseEntity.ok(taskService.getTaskByProject(project));
    }

    @PostMapping("/create")
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable("id") int id, @RequestBody Task task){
        taskService.updateTask(task, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") int id){
        taskService.deleteTaskById(id);
    }

}
