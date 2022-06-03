package com.example.demo.user;

import com.example.demo.mq.Producer;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path="/")
public class userController {

    private final userService userService;

    @Autowired
    private jwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
          );
      }catch (Exception ex){
          throw new Exception("invalid email or password");
      }
      return jwtUtil.generateToken(authRequest.getEmail());
    }

    @GetMapping
    public String getUsers(@RequestBody String message) throws IOException, TimeoutException {
        Producer producer =  new Producer();
        producer.produceMessage("getUsers", message);
        return userService.getUsers().toString();
    }

    @PostMapping("/addUser")
    public void registerNewUser(@RequestBody User user) throws IOException, TimeoutException {
        Producer producer =  new Producer();
        producer.produceMessage("getUsers", user.toString());
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) throws IOException, TimeoutException {
        Producer producer =  new Producer();
        producer.produceMessage("getUsers", userId.toString());
        userService.deleteUser(userId);
        return "user is deleted";
    }

    @PutMapping("update/{id}")
    public void update(@RequestBody User user, @PathVariable long id) throws IOException, TimeoutException {
        Producer producer =  new Producer();
        producer.produceMessage("getUsers", user.toString());
        userService.updateUser(id, user.getName(), user.getEmail(), user.getPassword());
    }

}

