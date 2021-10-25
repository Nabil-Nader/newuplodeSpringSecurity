package com.example.securityConfig;


import com.example.Service.AddUserRequest;

import com.example.mydomain.security.AppUserRole;
import com.example.mydomain.security.Role;
import com.example.mydomain.security.User;
import com.example.repository.security.RoleRepository;
import com.example.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


import java.util.*;
import java.util.stream.Collectors;

/*import static com.example.securityConfig.ApplicationRoles.STUDENT;
import static com.example.securityConfig.ApplicationRoles.TEACHER;*/

/**
 * Created by jt on 6/22/20.
 */
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private User user;
    private final RoleRepository roleRepository ;



    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name: " + username + " not found");
        });

     /*   UserDetails ud = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(TEACHER.getGrantedAuthorities())
                .build();
                return ud;
                */

        System.out.println("---------------------------------");

        System.out.println(user.getAuthorities());
        System.out.println("---------------------------------");

         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(),
                user.getAccountNonLocked(), user.getAuthorities());

    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return authorities;
    }

    public String signStudentUser(AddUserRequest appUser){
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword()) ;
        appUser.setPassword(encodedPassword);
        String username = appUser.getUsername();
        String password = appUser.getPassword();
        User myUser = new User();
        myUser.setPassword(password);
        myUser.setUsername(username);


        myUser.setAppUserRole(AppUserRole.ROLE_STUDENT);
        Role myrole = new Role(myUser.getId(),"STUDENT");

        Set<Role> addRole = new HashSet<>();
        addRole.add(myrole);
        myUser.setRoles(addRole);



        userRepository.save(myUser);
        roleRepository.save(myrole);

         return "save encoded user" ;
    }

    public String signTeacherUser(AddUserRequest appUser){
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword()) ;
        appUser.setPassword(encodedPassword);
        String username = appUser.getUsername();
        String password = appUser.getPassword();
        User myUser = new User();
        myUser.setPassword(password);
        myUser.setUsername(username);
        myUser.setAppUserRole(AppUserRole.ROLE_TEACHER);

        Role myrole = new Role(myUser.getId(),"TEACHER");

        Set<Role> addRole = new HashSet<>();
        addRole.add(myrole);
        myUser.setRoles(addRole);


        userRepository.save(myUser);
        roleRepository.save(myrole);


        return "save Teacher encoded user" ;
    }


}