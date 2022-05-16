//package com.todoist.list.service;
//
//import com.todoist.list.model.Subtask;
//import com.todoist.list.model.Task;
//import com.todoist.list.repo.SubtaskRepo;
//import com.todoist.list.repo.TaskRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@AllArgsConstructor
//@Service
//public class SubtaskService {
//    final private SubtaskRepo subtaskRepo;
//    final private TaskRepo taskRepo;
//
//    public Map createSubtask(Subtask subtask, String task_id) {
//        Optional<Task> task = taskRepo.findById(task_id);
//        if(task.isPresent()){
//            Task t = task.get();
//            if(t.getSubtasks() == null){
//                t.setSubtasks(new ArrayList<>());
//            }
//            t.getSubtasks().add(subtask);
//            t.setDate_added(LocalDate.now());
//            subtaskRepo.insert(subtask);
//            taskRepo.save(t);
//        }
//        Map result = new HashMap<String, Object>();
//        result.put("success", true);
//        result.put("data", subtask);
//        return result;
//    }
//}
