package com.taysirsoftware.store.services;

import com.taysirsoftware.store.entities.Product;

import jakarta.persistence.*;
import java.util.List;

public class ProductService {
    private EntityManager entityManager;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void updateProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }

    public void deleteProduct(Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
        entityManager.getTransaction().commit();
    }

    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<Product> getProduitsByCategorie(Long categorieId) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.categorie.idcategorie = :categorieId", Product.class)
                .setParameter("categorieId", categorieId)
                .getResultList();
    }
}
