package com.task.TaskManagement.controllers;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity clientEntity){
        return ResponseEntity.ok(clientService.save(clientEntity));
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable int clientId){
        return clientService.getByUserId(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientEntity> update(@PathVariable int clientId, @RequestBody ClientEntity clientEntity){
        return ResponseEntity.ok(clientService.update(clientId, clientEntity));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> delete(@PathVariable int clientId){
        clientService.delete(clientId);
        return ResponseEntity.ok("Client deleted with Id: " + clientId);
    }
}
