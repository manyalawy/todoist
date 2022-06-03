package com.example.demo.user;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.verify;




import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
class userControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    @MockBean
    userService service;


    @Autowired
    UserRepository repository;


    @Test
    void getUsersTest() throws Exception {


        /*"[User{id=1," +
                " name='Mariam', email='mariam@gmail.com', password='mariam123', userType='admin'}, " +
                "User{id=2, name='Ahmed', email='ahmed@gmail.com', password='ahmed123', userType='standard'}, User{id=3, name='Mahmoud', " +
                "email='mahmoud@gmail.com', password='mahmoud123', userType='standard'}, User{id=4, name='Aly', email='aly@gmail.com', " +
                "password='aly123', userType='standard'}, User{id=5, name='samar', email='samar@gmail.com', password='samar123', userType='" +
                "standard'}]"
        System.out.println(""+service.getUsers());
        mockmvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("[User{id=1," +
                " name='Mariam', email='mariam@gmail.com', password='mariam123', userType='admin'}, " +
                "User{id=2, name='Ahmed', email='ahmed@gmail.com', password='ahmed123', userType='standard'}]")));
        verify(service).getUsers();
       Long idFirst= Long.valueOf(1);
        Long idSecond= Long.valueOf(2);

        when(repository.findAll()).thenReturn(Stream.of(new User(idFirst,"Mariam","mariam@gmail.com","mariam123",
                "admin"),new User(idSecond,"Ahmed","ahmed@gmail.com","ahmed123","standard"))
                .collect(Collectors.toList()));
        assertEquals("[User{id=1," +
                " name='Mariam', email='mariam@gmail.com', password='mariam123', userType='admin'}, " +
                "User{id=2, name='Ahmed', email='ahmed@gmail.com', password='ahmed123', userType='standard'}]",service.getUsers());*/


    }

    @Test
    void registerNewUserTest() throws Exception {

     //   Long id = Long.valueOf(3);
       // User user = new User(id,"Bilal", "bilal@gmail.com","bilal123","standard");
      //   mockmvc.perform(post("/addUser", user)).andDo(print()).andExpect(status().isOk());
//        mockmvc.perform(MockMvcRequestBuilders.post("/addUser").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Bilal\",\"email\":\"bilal@gmail.com\",\"password\":\"bilal123\",\"userType\":\"standard\"}"))
//                .andExpect(MockMvcResultMatchers.status().isOk());

        //verify(service).addNewUser(any(User.class));
        //when(repository.save(user)).thenReturn(user);
        //assertEquals(user,service.addNewUser(user));
        Long id= Long.valueOf(3);

        User user=new User();
        user.setName("Bilal");
        user.setEmail("bilal@gmail");
        user.setPassword("bilal123");
        user.setId(id);
        repository.save(user);
        assertNotNull(repository.findUserById(id).get());
    }

    @Test
    void deleteUserTest() throws Exception {
        Long id= Long.valueOf(2);
       mockmvc.perform(MockMvcRequestBuilders.delete("/delete/2").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).deleteUser(id);

      /*  Mockito.when(service.deleteUser(id)).thenReturn("SUCCESS");
        mockmvc.perform(MockMvcRequestBuilders.delete("/delete/2", id))
                .andExpect(status().isOk());*/


    }

    @Test
    void updateTest() throws Exception {
        Long id= Long.valueOf(2);
        mockmvc.perform(MockMvcRequestBuilders.put("/update/2").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Badr\",\"email\":\"badr@gmail.com\",\"password\":\"badr123\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}