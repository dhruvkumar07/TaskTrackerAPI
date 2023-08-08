package com.taskTracker.assignment.services;

import com.taskTracker.assignment.dto.TaskDTO;

import java.util.List;

public interface TaskServices {

    TaskDTO createNewTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(String id);
    TaskDTO updateTaskById(String id , TaskDTO taskDTO);

    void deleteTaskById(String id);
}
