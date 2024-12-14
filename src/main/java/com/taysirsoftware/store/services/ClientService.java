package com.taysirsoftware.store.services;

import com.taysirsoftware.store.entities.Client;

import jakarta.persistence.*;
import java.util.List;

public class ClientService {
    private EntityManager entityManager;

    public ClientService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    public void updateClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
    }

    public void deleteClient(Long id) {
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
        entityManager.getTransaction().commit();
    }

    public List<Client> getAllClients() {
        return entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }
}
