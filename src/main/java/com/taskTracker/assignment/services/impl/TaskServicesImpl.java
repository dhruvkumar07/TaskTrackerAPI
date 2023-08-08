package com.taskTracker.assignment.services.impl;

import com.taskTracker.assignment.dto.TaskDTO;
import com.taskTracker.assignment.module.Task;
import com.taskTracker.assignment.repositories.TaskRepository;
import com.taskTracker.assignment.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServicesImpl implements TaskServices {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskServicesImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskDTO createNewTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());

        Task savedTask = taskRepository.save(task);

        TaskDTO taskResponse = new TaskDTO();
        taskResponse.setId(savedTask.getId());
        taskResponse.setTitle(savedTask.getTitle());
        taskResponse.setDescription(savedTask.getDescription());
        taskResponse.setDueDate(savedTask.getDueDate());

        return taskResponse;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> list = taskRepository.findAll();
        return list.stream().map(t -> maptoDTO(t)).collect(Collectors.toList());
    }
    @Override
    public TaskDTO getTaskById(String id){
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("No Task found for given ID")));
        return maptoDTO(task);
    }

    @Override
    public TaskDTO updateTaskById(String id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("No Task found for given ID")));

        if(taskDTO.getTitle() != null) task.setTitle(taskDTO.getTitle());
        if(taskDTO.getDescription() != null) task.setDescription(taskDTO.getDescription());
        if(taskDTO.getDueDate() != null) task.setDueDate(taskDTO.getDueDate());

        Task taskUpdated = taskRepository.save(task);
        return maptoDTO(taskUpdated);
    }

    @Override
    public void deleteTaskById(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND , String.format("No Task for given ID")));

        taskRepository.delete(task);
    }

    private TaskDTO maptoDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());

        return taskDTO;
    }
}
