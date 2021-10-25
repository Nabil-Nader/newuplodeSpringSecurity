package com.example.securityConfig.cofig;

import com.example.Service.MyAppUserService;
import com.example.securityConfig.JpaUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.mydomain.security.AppUserRole.ROLE_STUDENT;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JpaUserDetailsService jpaUserDetailsService;





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,"/api/**").permitAll()

//                .antMatchers("api/**").hasAuthority(ROLE_STUDENT.name())
//                .antMatchers("api/**").hasAuthority("ROLE_STUDENT")
//                .antMatchers("api/getstudent/**").hasAuthority("ROLE_STUDENT")
//                .antMatchers("api/getstudent/**").hasAuthority("ROLE_STUDENT")
////                .antMatchers(HttpMethod.GET,"api/**").hasAnyAuthority(STUDENT_READ.getPermission())

                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(jpaUserDetailsService);



        return provider;
    }



/*
    @Override
    @Bean // this is how we  can retrieve a user from db
    protected UserDetailsService userDetailsService() {
         UserDetails studentAdmin = User.builder()
                 .username("student")
                 .password(passwordEncoder.encode("student"))
                 .roles("ADMIN")
                 .build();

         UserDetails user= User.builder()
                 .username("user")
                 .password(passwordEncoder.encode("user"))
                 .roles("USER")
                 .build();

        return new InMemoryUserDetailsManager
                (
                        studentAdmin,
                        user

                );
    }*/
}
