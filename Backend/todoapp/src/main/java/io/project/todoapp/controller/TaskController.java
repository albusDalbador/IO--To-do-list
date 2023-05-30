package io.project.todoapp.controller;

import io.project.todoapp.model.Subject;
import io.project.todoapp.model.Task;
import io.project.todoapp.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/v1/tasks/{semesterId}/{subjectId}")
    public void addNewTask(@RequestBody Task task, @PathVariable("semesterId") Long semesterId, @PathVariable("subjectId") Long subjectId) {
        taskService.addNewTask(task, semesterId, subjectId);
    }
}