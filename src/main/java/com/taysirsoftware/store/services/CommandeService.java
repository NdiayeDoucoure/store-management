package com.taysirsoftware.store.services;

import com.taysirsoftware.store.entities.Commande;

import jakarta.persistence.*;
import java.util.List;

public class CommandeService {
    private EntityManager entityManager;

    public CommandeService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addCommande(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.persist(commande);
        entityManager.getTransaction().commit();
    }

    public void updateCommande(Commande commande) {
        entityManager.getTransaction().begin();
        entityManager.merge(commande);
        entityManager.getTransaction().commit();
    }

    public void deleteCommande(Long id) {
        entityManager.getTransaction().begin();
        Commande commande = entityManager.find(Commande.class, id);
        if (commande != null) {
            entityManager.remove(commande);
        }
        entityManager.getTransaction().commit();
    }

    public List<Commande> getAllCommandes() {
        return entityManager.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
    }
}
