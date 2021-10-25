/*
package com.example.securityConfig;

import com.google.common.collect.Sets;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.securityConfig.ApplicationUserPermission.*;


public enum ApplicationRoles {
    STUDENT(Sets.newHashSet()),
    TEACHER(Sets.newHashSet(Teacher_READ, Teacher_WRITE,STUDENT_READ,STUDENT_WRITE));

    private final Set<ApplicationUserPermission> userPermissionsen ;


    ApplicationRoles(Set<ApplicationUserPermission> userPermissionsen) {
        this.userPermissionsen = userPermissionsen;
    }

    public Set<ApplicationUserPermission> getPermissions(){
        return userPermissionsen;
    }

    */
/**
     * Method return full user Role/Role
     * @return
     *//*


    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        System.out.println(permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name())));

        return permissions ;

    }

}
*/
