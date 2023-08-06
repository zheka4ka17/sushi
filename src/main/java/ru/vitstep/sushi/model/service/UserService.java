package ru.vitstep.sushi.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
    return userRepository.findAll();
    }

    public User findById(Long id){
    return userRepository.findById(id).orElse(null);
    }

    public void createOrUpdate(User user){
    userRepository.save(user);
    }

    public void deleteById(Long id){
    userRepository.deleteById(id);
    }


}
