package com.task.TaskManagement.dao.impl;

import com.task.TaskManagement.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {
}
