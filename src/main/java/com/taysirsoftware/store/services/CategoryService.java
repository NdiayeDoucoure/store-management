package com.taysirsoftware.store.services;

import com.taysirsoftware.store.entities.Category;

import jakarta.persistence.*;
import java.util.List;

public class CategoryService {

    private final EntityManagerFactory emf;

    // Initialization of the EntityManagerFactory with the persistence unit defined in persistence.xml
    public CategoryService(EntityManager em) {
        this.emf = Persistence.createEntityManagerFactory("storePU");
    }

    // Method to add a category
    public void addCategory(Category category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category); // Persist the object in the database
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Propagate the exception
        } finally {
            em.close();
        }
    }

    // Method to list all categories
    public List<Category> getAllCategories() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Method to update a category
    public void updateCategory(Category category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category); // Update the object in the database
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Propagate the exception
        } finally {
            em.close();
        }
    }

    // Method to delete a category
    public void deleteCategory(Long categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, categoryId);
            if (category != null) {
                em.remove(category); // Remove the object from the database
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Propagate the exception
        } finally {
            em.close();
        }
    }
}