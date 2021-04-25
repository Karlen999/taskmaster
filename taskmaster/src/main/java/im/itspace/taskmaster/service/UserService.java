package im.itspace.taskmaster.service;

import im.itspace.taskmaster.exception.UserNotFoundException;
import im.itspace.taskmaster.model.User;
import im.itspace.taskmaster.model.UserType;
import im.itspace.taskmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


    public List<User> findAllByUserType(UserType userType){
        return userRepository.findAllByUserType(userType);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User update(User user, int id){
        User userFromDb = userRepository.findById(id).orElseThrow( () ->new UserNotFoundException ("User does not exists"));
        userFromDb.setName(user.getName());
        userFromDb.setSurname(user.getSurname());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        return userRepository.save(userFromDb);
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }



}
