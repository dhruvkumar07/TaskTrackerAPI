package com.taskTracker.assignment.repositories;

import com.taskTracker.assignment.module.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task , String> {
}
