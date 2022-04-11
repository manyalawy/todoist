
package com.todoist.list.service;

import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TodoListRepo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TodolistServiceTest {

    @Mock
    private TodoListRepo todoListrepo;

    @InjectMocks
    private TodolistService todolistservice;


    TodoList RECORD_1 = new TodoList("1", "Test1", null, null,null);
    TodoList RECORD_2 = new TodoList("2", "Test2", null, null,null);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void createList() {
    }

    @Test
    void searchlistbyname() {

        Mockito.when(todoListrepo.findByName("Test1")).thenReturn(Arrays.asList((RECORD_1)));

        //calling method under the test
        List<TodoList> result  = todolistservice.searchlistbyname("Test1");
        assertThat(result).hasSize(1);
        //assert

        assertThat(result.get(0).getId()).isEqualTo("1");

        //verify that repository was called
        verify(todoListrepo, times(1)).findByName("Test1");
    }
}


