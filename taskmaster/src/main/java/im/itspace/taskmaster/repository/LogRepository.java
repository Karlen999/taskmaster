package im.itspace.taskmaster.repository;

import im.itspace.taskmaster.model.Log;
import im.itspace.taskmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findAllByUser(User user);

    List<Log> findAllByTaskId(int id);
}
