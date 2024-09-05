package com.example.user_management.controller;

import com.example.user_management.task_management.dao.entity.TaskEntity;
import com.example.user_management.task_management.dto.TaskDto;
import com.example.user_management.task_management.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")

public class TaskController {
private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<TaskEntity> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/{taskId}")
    public TaskEntity getTask(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PostMapping
    @Operation(summary = "This endpoint helps us to add a new task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The request was successful"),
                    @ApiResponse(responseCode = "400", description = "There is incoming request validation error"),
                    @ApiResponse(responseCode = "409", description = "There is a conflict with the current state of the resource, preventing the request from being completed."),
                    @ApiResponse(responseCode = "417", description = "The server cannot meet the expectations specified in the request"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")
            })
    public void addTask(@RequestBody TaskDto taskDto){
        taskService.addTask(taskDto);
    }
    @PutMapping("/{taskId}")
    @Operation(summary = "This endpoint is for deleting task",
    responses = {
            @ApiResponse(responseCode = "200", description = "The request was successful"),
            @ApiResponse(responseCode = "400", description = "There is incoming request validation error"),
            @ApiResponse(responseCode = "409", description = "There is a conflict with the current state of the resource, preventing the request from being completed."),
            @ApiResponse(responseCode = "417", description = "The server cannot meet the expectations specified in the request"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")

    })
    public void editTask(@PathVariable Long taskId, @RequestBody TaskDto updatedTaskDto){
        taskService.editTask(taskId, updatedTaskDto);
    }
    @DeleteMapping("/{taskId}")
    @Operation(summary = "This endpoint helps us to delete a task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The request was successful"),
                    @ApiResponse(responseCode = "404", description = "The task with the specified ID was not found"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")
            })
    public void deleteTask(@PathVariable Long taskId) {
         taskService.deleteTask(taskId);
    }
}
