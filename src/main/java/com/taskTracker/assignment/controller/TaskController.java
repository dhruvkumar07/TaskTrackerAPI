package com.taskTracker.assignment.controller;

import com.taskTracker.assignment.dto.TaskDTO;
import com.taskTracker.assignment.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class TaskController {

    private TaskServices taskServices;
    @Autowired
    public TaskController(TaskServices taskServices){
        this.taskServices = taskServices;
    }
    @PostMapping("task/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDTO> createNewTask(@RequestBody TaskDTO taskDTO){
        TaskDTO taskDTOResponse = taskServices.createNewTask(taskDTO);
        return new ResponseEntity<>(taskDTOResponse , HttpStatus.CREATED);
    }

    @GetMapping("tasks")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
        List<TaskDTO> list = taskServices.getAllTasks();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    @GetMapping("task/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable String id){
        TaskDTO taskDTO = taskServices.getTaskById(id);
        return new ResponseEntity<>(taskDTO , HttpStatus.OK);
    }

    @PutMapping("task/{id}/update")
    public ResponseEntity<TaskDTO> updateTaskById(@PathVariable String id , @RequestBody TaskDTO taskDTO) throws ChangeSetPersister.NotFoundException {
        if(taskDTO == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        return new ResponseEntity<>(taskServices.updateTaskById(id , taskDTO) , HttpStatus.OK);
    }

    @DeleteMapping("task/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        taskServices.deleteTaskById(id);
        return new ResponseEntity<>("Task Deleted" , HttpStatus.OK);
    }
}
