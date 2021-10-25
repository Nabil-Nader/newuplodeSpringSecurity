package com.example.Service;


import com.example.mydomain.security.User;
import com.example.repository.security.UserRepository;
import com.example.securityConfig.JpaUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyAppUserService {

    private final UserRepository userRepository;
    private final JpaUserDetailsService jpaUserDetailsService ;

    public User addMyUser(User user) {
        return userRepository.save(user);
    }


    public String add(AddUserRequest request){
        return jpaUserDetailsService.signStudentUser(request);

    }

    public User getUserStudent(Long id){
        return userRepository.findUserById(id);
    }

}
