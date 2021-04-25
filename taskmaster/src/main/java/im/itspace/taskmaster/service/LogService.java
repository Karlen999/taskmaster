package im.itspace.taskmaster.service;


import im.itspace.taskmaster.dto.LogCreateRequest;
import im.itspace.taskmaster.model.Log;
import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.repository.LogRepository;
import im.itspace.taskmaster.repository.ProjectRepository;
import im.itspace.taskmaster.repository.TaskRepository;
import im.itspace.taskmaster.security.CurrentUser;
import im.itspace.taskmaster.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final SecurityService securityService;

    public Log addLog(LogCreateRequest logCreateRequest) {
        CurrentUser user = securityService.getCurrentUser();
        Task task = taskService.findById(logCreateRequest.getTaskId());
        task.setLogTime(task.getLogTime() + logCreateRequest.getHours());
        taskRepository.save(task);
        return logRepository.save(logCreateRequest, user, task);
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public List<Log> getAllByUser(User user) {
        return logRepository.findAllByUser(user);
    }

    public Log findById(int id) {
        return logRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) { logRepository.deleteById(id); }

    public List<Log> findAllByTaskId(int taskId) {
        return logRepository.findAllByTaskId(taskId);
    }

}
