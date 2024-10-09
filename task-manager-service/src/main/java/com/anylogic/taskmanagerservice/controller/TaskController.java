package com.anylogic.taskmanagerservice.controller;

import com.anylogic.taskmanagerservice.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Boolean> stopTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.stopTask(taskId));
    }
}
