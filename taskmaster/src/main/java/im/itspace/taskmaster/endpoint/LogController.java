package im.itspace.taskmaster.endpoint;


import im.itspace.taskmaster.dto.LogCreateRequest;
import im.itspace.taskmaster.model.Log;
import im.itspace.taskmaster.model.Task;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LogCreateRequest logCreateRequest) {
        Log log = logService.addLog(logCreateRequest);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Log>> getAllLog() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/logs-user")
    public ResponseEntity<List<Log>> getAllByUser(@RequestBody User user) {
        return ResponseEntity.ok(logService.getAllByUser(user));
    }

    @GetMapping("/logs-task/{id}")
    public ResponseEntity<List<Log>> getAllByTask(@PathVariable("id") int id ) {
        return ResponseEntity.ok(logService.findAllByTaskId(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Log> findById(@PathVariable int id) {

        return ResponseEntity.ok(logService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        logService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
