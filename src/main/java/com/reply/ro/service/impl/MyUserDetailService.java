package com.reply.ro.service.impl;

import com.reply.ro.models.Role;
import com.reply.ro.models.User;
import com.reply.ro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User > userOptional = userRepository.findUserByUsername(username);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;


        if(userOptional.isPresent()){
            User user = userOptional.get();

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());

            Set<GrantedAuthority > authoritySet = new HashSet<>();

            for(Role a : user.getRoles()){
                authoritySet.add(
                        new SimpleGrantedAuthority("ROLE_" + a.getName())
                );
            }

            builder.authorities(authoritySet);

        }else{
            throw new UsernameNotFoundException("User not found man!");
        }

        return builder.build();
    }
}
