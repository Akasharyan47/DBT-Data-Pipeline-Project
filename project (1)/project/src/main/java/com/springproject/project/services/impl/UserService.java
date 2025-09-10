package com.springproject.project.services.impl;


import com.springproject.project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public abstract class UserServicesImpl implements UserDetailsService{

    private final UserRepository userRepository ;



    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
  public UserDetailsService userDetailsService(Object username){
      return userDetailsService(username){

          @Override
         public UserDetails loadUserByUsername(String username){
             return userRepository.findByEmail(username)
                     .orElseThrow(() -> new UsernameNotFoundException("user not found"));
          }

      }
    }
}
