package com.example.lasresume.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomerUserDetailsServicempl implements UserDetailsService {
    private UserRepositoryCustom userRepositoryCustom;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepositoryCustom.findByEmail(username);
        User.UserBuilder builder=null;
        if(user!=null){
            builder=userRepositoryCustom.User.withUsername(username);
            builder.disabled(false);
            builder.password(user.getPassword());
            String [] authoritiesArr=new String[]{"ADMIN", "USER"};
            builder.authorities(authoritiesArr);
        }
    else{
        throw new UsernameNotFoundException("User not found");
    }
    return builder.build();
}
