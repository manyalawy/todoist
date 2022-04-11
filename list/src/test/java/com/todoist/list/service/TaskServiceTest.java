package com.todoist.list.service;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TaskServiceTest {


    @Mock
    private TaskRepo taskrepo;

    @InjectMocks
    private TaskService taskservice;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    Task T = new Task("1","Test1",false,null,null,null,null,null,null);







    @Test
    void createTask() {
    }

    @Test
    void gettaskid() {

        given(taskrepo.findById("1")).willReturn(Optional.of(T));

        //calling method under the test
        Optional<Task> result  = taskservice.gettaskid("1");

        //assert

        assertThat(result.get()).isEqualTo(T);



    }

    @Test
    void assigntask() {


        Mockito.when(taskrepo.save(T)).thenReturn(T);

        Task updated=taskservice.assigntask(Optional.of(T),"new");

        assertThat(updated.getAssignee()).contains("new");




    }

    @Test
    void updatetask() {


        Task Tnew2 = new Task("1","Test1",true,null, null,"low",null,null,null);
        Mockito.when(taskrepo.save(T)).thenReturn(T);

        Task updated=taskservice.updatetask(Optional.of(T),Tnew2);

        assertThat(updated.getPriority()).isEqualTo(Tnew2.getPriority());
        assertThat(updated.getDone()).isEqualTo(Tnew2.getDone());








    }
}