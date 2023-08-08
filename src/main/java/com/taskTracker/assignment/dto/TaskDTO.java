package com.taskTracker.assignment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private String id;
    private String title;
    private String description;
    private Date dueDate;
}
