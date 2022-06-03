package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class userService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public userService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUsers() {
        return userRepository.findAll().toString();
    }

    public String addNewUser(User user) {

        Optional<User>
                userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email already exists");
        }


        userRepository.save(user);
        return "user is added";
    }

    public String deleteUser(Long userId) {

        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
        return "user is deleted";
    }





   @Transactional
    public String updateUser(Long userId, String name, String email, String password) {
        String x="";
        User user= userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
            "Student with id" + userId + "does not exist"
        ));
        if(name != null && name.length()>0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
            x=x+"name is changed";
        }
        if(email !=null && email.length()>0 && !Objects.equals(user.getEmail(),email)){
           Optional<User>userOptional=userRepository.findUserByEmail(email);
           if(userOptional.isPresent()){
               throw new IllegalStateException("email Taken");
           }

            user.setEmail(email);
           x=x+ ", Email has been updated";
        }
        if(password !=null && password.length()>0){
            user.setPassword(password);
            x=x+", password updated";
        }
        return x;
    }


   

}
