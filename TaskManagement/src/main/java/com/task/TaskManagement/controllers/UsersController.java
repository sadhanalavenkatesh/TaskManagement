package com.task.TaskManagement.controllers;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.Entity.UsersEntity;
import com.task.TaskManagement.dto.Clientdto;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.dto.UserDto;
import com.task.TaskManagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

//    @PostMapping
//    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) {
//        return ResponseEntity.ok(userService.save(user));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UsersEntity>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAll());
//    }
//
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<UsersEntity> update(@PathVariable int userId, @RequestBody UsersEntity user) {
//        return ResponseEntity.ok(userService.update(userId, user));
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> delete(@PathVariable int userId) {
//        userService.delete(userId);
//        return ResponseEntity.ok("User deleted with Id: " + userId);
//    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<UsersEntity>> createClient(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UsersEntity>> updateClient(@PathVariable Integer id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteClient(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<UsersEntity>> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<UsersEntity>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
