package com.example.controller;

import com.example.Exception.UserNotFoundException;
import com.example.Service.AddUserRequest;
import com.example.Service.MyAppUserService;
import com.example.mydomain.security.User;
import com.example.securityConfig.JpaUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class MyUserController {
    private final MyAppUserService myAppUserService;
    private final JpaUserDetailsService jpaUserDetailsService ;

    @PostMapping("adduser")

    public String addStudent(@RequestBody AddUserRequest user){
        return  jpaUserDetailsService.signStudentUser(user);
    }

    @PostMapping("addteacher")

    public String addTeacher(@RequestBody AddUserRequest user){
        return  jpaUserDetailsService.signTeacherUser(user);
    }

    @GetMapping("/student/{id}")
//    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public User getUser(@PathVariable("id") Long id){
        User user = myAppUserService.getUserStudent(id);
         System.out.println(user.toString());
         return user;
    }

}
