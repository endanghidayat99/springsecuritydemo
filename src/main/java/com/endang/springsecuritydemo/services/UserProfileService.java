package com.endang.springsecuritydemo.services;

import com.endang.springsecuritydemo.models.UserProfile;
import com.endang.springsecuritydemo.repositories.UserProfileRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileService implements UserDetailsService {

    @Autowired
    UserProfileRepositories repositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile usr = repositories.findByUsername(username);
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(usr.getRole());
        grantList.add(authority);

        UserDetails userDetails = new User(usr.getUsername(),usr.getPassword(),grantList);
        return userDetails;
    }
}
