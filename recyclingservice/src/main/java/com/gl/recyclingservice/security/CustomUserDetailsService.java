package com.gl.recyclingservice.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new org.springframework.security.core.userdetails.User(
                email,
                "",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}