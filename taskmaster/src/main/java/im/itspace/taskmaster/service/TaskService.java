package im.itspace.taskmaster.service;

import im.itspace.taskmaster.exception.ResourceNotFoundException;
import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.Status;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Task task, int id) {
        Task taskFromDb = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
        taskFromDb.setTitle(task.getTitle());
        taskFromDb.setStartDate(task.getStartDate());
        taskFromDb.setDeadLine(task.getDeadLine());
        taskFromDb.setUsers(task.getUsers());
        taskFromDb.setProject(task.getProject());
        taskFromDb.setLogTime(task.getLogTime());
        taskFromDb.setStatus(task.getStatus());
        return taskRepository.save(taskFromDb);
    }


    public List<Task> getByStatus(Status status) {

        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findAllTasksByUsers(user);
    }

    public Optional<Task> getTaskByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    public void deleteTaskById(int id) throws ResourceNotFoundException {
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Task does not exist.");
        }
    }

    public Task findById(int id) {

        return taskRepository.findById(id).orElse(null);
    }
}
