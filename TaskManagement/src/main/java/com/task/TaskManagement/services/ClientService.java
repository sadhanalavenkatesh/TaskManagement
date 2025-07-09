package com.task.TaskManagement.services;

import com.task.TaskManagement.Entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

ClientEntity save(ClientEntity clientEntity);
    List<ClientEntity> getAll();
    Optional<ClientEntity> getByUserId(int clientId);
    void delete(int clientId);
    ClientEntity update(int clientId, ClientEntity clientEntity);

}
