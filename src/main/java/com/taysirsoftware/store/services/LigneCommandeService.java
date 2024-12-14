package com.taysirsoftware.store.services;

import com.taysirsoftware.store.entities.LigneCommande;

import jakarta.persistence.*;
import java.util.List;

public class LigneCommandeService {
    private EntityManager entityManager;

    public LigneCommandeService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addLigneCommande(LigneCommande ligneCommande) {
        entityManager.getTransaction().begin();
        entityManager.persist(ligneCommande);
        entityManager.getTransaction().commit();
    }

    public void updateLigneCommande(LigneCommande ligneCommande) {
        entityManager.getTransaction().begin();
        entityManager.merge(ligneCommande);
        entityManager.getTransaction().commit();
    }

    public void deleteLigneCommande(Long id) {
        entityManager.getTransaction().begin();
        LigneCommande ligneCommande = entityManager.find(LigneCommande.class, id);
        if (ligneCommande != null) {
            entityManager.remove(ligneCommande);
        }
        entityManager.getTransaction().commit();
    }

    public List<LigneCommande> getLignesByCommande(Long commandeId) {
        return entityManager.createQuery("SELECT l FROM LigneCommande l WHERE l.commande.id = :commandeId", LigneCommande.class)
                .setParameter("commandeId", commandeId)
                .getResultList();
    }

    public List<LigneCommande> getAllLigneCommandes() {
        return entityManager.createQuery("SELECT l FROM LigneCommande l", LigneCommande.class)
                .getResultList();
    }
}
