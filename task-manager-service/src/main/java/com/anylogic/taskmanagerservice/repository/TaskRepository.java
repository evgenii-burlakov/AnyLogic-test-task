package com.anylogic.taskmanagerservice.repository;

import com.anylogic.taskmanagerservice.dto.TaskStatus;
import com.anylogic.taskmanagerservice.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE TaskEntity t SET t.status = :status WHERE t.id = :id")
    void updateTaskStatus(@Param("id") Long id, @Param("status") TaskStatus status);
}
