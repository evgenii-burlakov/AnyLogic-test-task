package com.anylogic.taskmanagerservice.repository;

import com.anylogic.taskmanagerservice.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TaskRepository extends JpaRepository<TaskEntity, Long> {
}
