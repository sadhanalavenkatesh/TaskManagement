package com.task.TaskManagement.services.Impl;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.dao.ClientRepository;
import com.task.TaskManagement.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    @Override
    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> getByUserId(int clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public ClientEntity update(int clientId, ClientEntity clientEntity) {
        Optional<ClientEntity> existing = clientRepository.findById(clientId);
        if (existing.isPresent()) {
ClientEntity data = existing.get();
data.setClientName(clientEntity.getClientName());
data.setAddress(clientEntity.getAddress());
data.setPinCode(clientEntity.getPinCode());
data.setIndustry(clientEntity.getIndustry());
data.setGstNumber(clientEntity.getGstNumber());
data.setConatctEmail(clientEntity.getConatctEmail());
data.setContactNumber(clientEntity.getContactNumber());
data.setContactPerson(clientEntity.getContactPerson());
data.setCreatedAt(clientEntity.getCreatedAt());
data.setUpdatedAt(clientEntity.getUpdatedAt());
data.setRemarks(clientEntity.getRemarks());
            return clientRepository.save(data);
        } else{

            throw new RuntimeException("User not found with ClientId: " + clientId);
        }
    }

    @Override
    public void delete(int clientId) {
      clientRepository.deleteById(clientId);
    }
}
