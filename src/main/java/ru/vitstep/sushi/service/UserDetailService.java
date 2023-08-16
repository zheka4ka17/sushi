package ru.vitstep.sushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vitstep.sushi.model.User;
import ru.vitstep.sushi.repository.UserRepository;
import ru.vitstep.sushi.security.UserDetail;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
@Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);


        if (user.isEmpty())
            throw new UsernameNotFoundException("User '" + username + "' not found");

        return new UserDetail(user.get()) ;


    }
}
