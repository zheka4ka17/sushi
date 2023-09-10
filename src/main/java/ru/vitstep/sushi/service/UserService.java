package ru.vitstep.sushi.service;

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

    public void save(User user){
    userRepository.save(user);
    }

    public void delete(Long id){
    userRepository.delete(userRepository.findById(id).get());
    }

    public User findByEmail(String email){
    return userRepository.findByEmail(email).orElse(null);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }
    public User findByPhoneNumber(int phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }


    public void update(Long id, User updatedUser){
    updatedUser.setId(id);
    userRepository.save(updatedUser);
    }


}
