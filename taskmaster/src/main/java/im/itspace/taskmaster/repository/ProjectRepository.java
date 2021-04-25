package im.itspace.taskmaster.repository;

import im.itspace.taskmaster.model.Project;
import im.itspace.taskmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByName(String name);

    Optional<Project> findByUser(User user);

    Optional<Project> findById(int id);


}
