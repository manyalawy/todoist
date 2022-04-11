
package com.todoist.list.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TodoListRepo;
import com.todoist.list.service.TodolistService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoListController.class)
class TodoListControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    ObjectMapper mapper;

    @MockBean
    private TodoListRepo todolistrepo;

    @MockBean
    private TodolistService todoListservice;


    TodoList RECORD_1 = new TodoList("1", "Test1", null, null,null);
    TodoList RECORD_2 = new TodoList("2", "Test1", null, null,null);
    TodoList RECORD_3 = new TodoList("3", "Test2", null, null,null);




    @Test
    void createList() {
    }

    @Test
    void searchlist_success() throws Exception {



       List<TodoList> records = new ArrayList(Arrays.asList(RECORD_1,RECORD_2));
//        List<TodoList> records = new ArrayList<>();
//        records.add(RECORD_1);


        Mockito.when(todoListservice.searchlistbyname("Test1")).thenReturn((records));



        mockMvc.perform(get("/api/todolist/search/{name}","Test1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].name", is("Test1")));







    }



    @Test
    void searchlist_notFound() throws Exception {



        List<TodoList> recordss = new ArrayList();


        Mockito.when(todoListservice.searchlistbyname("Test5")).thenReturn((recordss));



        mockMvc.perform(get("/api/todolist/search/{name}","Test5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result ->
                        assertTrue(result.getResponse().getStatus() == 404)
                );







    }
}














