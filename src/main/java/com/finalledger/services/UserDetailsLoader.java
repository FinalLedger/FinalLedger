package com.finalledger.services;

import com.finalledger.models.User;
import com.finalledger.models.UserWithRoles;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService{
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users){
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername (String username ) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if(user== null){
            throw new UsernameNotFoundException("No User found for " + username);
        }
        return new UserWithRoles(user);
    }

}
