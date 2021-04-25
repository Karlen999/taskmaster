package im.itspace.taskmaster.repository;

import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.Status;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByTitle (String title);

    List<Task> findAllTasksByUsers(User user);

    List<Task> findByStatus(Status status);

    Optional<Task> findByProject(Project project);

    Optional<Task> findById(int id);
}
