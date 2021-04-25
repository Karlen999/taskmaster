package im.itspace.taskmaster.endpoint;


import im.itspace.taskmaster.dto.AuthRequest;
import im.itspace.taskmaster.dto.AuthResponse;
import im.itspace.taskmaster.dto.UserDto;
import im.itspace.taskmaster.mapper.UserMapper;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.model.UserType;
import im.itspace.taskmaster.repository.UserRepository;
import im.itspace.taskmaster.service.UserService;
import im.itspace.taskmaster.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    //    login
    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthRequest authRequest) {
        Optional<User> byEmail = userRepository.findByEmail(authRequest.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                String token = jwtTokenUtil.generateToken(user.getEmail());
                AuthResponse authResponse = new AuthResponse(token, user.getName(), user.getSurname());
                return ResponseEntity.ok(authResponse);
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/all")
    public List<User> users(@RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "20") int size){
        PageRequest pageRequest = PageRequest.of(-1, size);
        Page<User> users = userService.findAllUsers(pageRequest);
        return userService.findAllUsers();
    }

    @GetMapping("/all-members")
    public List<User> allMembers(){
        return userService.findAllByUserType(UserType.EMPLOYER);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> byId(@PathVariable("id") int id){
        User findById = userService.findById(id);
        UserDto userDto = modelMapper.map(findById, UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> save(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User save = userService.save(user);
            UserDto userDto = modelMapper.map(save, UserDto.class);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") int id){
        return userService.update(user,id);
    }
}
