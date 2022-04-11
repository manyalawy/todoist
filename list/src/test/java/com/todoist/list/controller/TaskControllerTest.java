package com.todoist.list.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import com.todoist.list.service.TaskService;
import com.todoist.list.service.TodolistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private TaskRepo taskrepo;

    @MockBean
    private TaskService taskservice;
    @MockBean
    private TodolistService todoListservice;



   Task T = new Task("1","Test1",false,null,null,null,null,null,null);



    @Test
    void createTask() {
    }

    @Test
    void editlist()  {

//
//        Task T2 = new Task("1","Test1",false,null,null,null,null,null,null);
//
//
//        Task Tnew = new Task("88","Test88",false,null, null,"low",null,null,null);
//
//       Mockito.when(taskservice.gettaskid("1")).thenReturn(Optional.of(T2));
//
//        Mockito.when(taskservice.updatetask(Optional.of(T2),Tnew)).thenReturn(Tnew);
//
//
//        mockMvc.perform(put("/api/todolist/edit/{id}","1")
//                        .contentType(MediaType.APPLICATION_JSON) .content(this.mapper.writeValueAsString(Tnew)))
//
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Test1")));
//




    }

    @Test
    void assigntotask() throws Exception {

        List<String> namenew = new ArrayList(Arrays.asList("TestName"));

        Task Tnew = new Task("1","Test1",false,null, namenew,null,null,null,null);





        // given(todoListservice.searchlistbyname("Test1")).willReturn((records));
//
        Mockito.when(taskservice.gettaskid(T.getId())).thenReturn(Optional.of(T));
        Mockito.when(taskservice.assigntask(Optional.of(T),"TestName")).thenReturn(Tnew);



        mockMvc.perform(put("/api/todolist/assignee/add/{name}","TestName")
                        .contentType(MediaType.APPLICATION_JSON) .content(this.mapper.writeValueAsString(T)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test1")))
               .andExpect(jsonPath("$.assignee ", hasItem("TestName")));






    }



}