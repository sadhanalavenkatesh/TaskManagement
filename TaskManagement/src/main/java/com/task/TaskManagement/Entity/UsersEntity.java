package com.task.TaskManagement.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;



}
